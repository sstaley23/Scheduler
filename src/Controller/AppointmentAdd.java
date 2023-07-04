package Controller;

import DB.AppointmentsDB;
import DB.ContactsDB;
import DB.CustomerDB;
import DB.UsersDB;
import Utilities.TimeManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

/** Allows user to add appointments <br>
 * Requirements satisfied in this Controller: A3a, A3d
 * */
public class AppointmentAdd implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    public TextField txtID;
    @FXML
    public TextField txtTitle;
    @FXML
    public TextField txtDescription;
    @FXML
    public ComboBox comboContact;
    @FXML
    public TextField txtLocation;
    @FXML
    public TextField txtType;
    @FXML
    public DatePicker dateStart;
    @FXML
    public DatePicker dateEnd;
    @FXML
    public ComboBox comboStartTime;
    @FXML
    public ComboBox comboEndTime;
    @FXML
    public ComboBox comboCustomer;
    @FXML
    public ComboBox comboUserID;
    @FXML
    public Label txtDialogue;


    /**
     * Method navigates back to the customer view
     *
     * @param actionEvent
     * @throws IOException
     */
    public void onActionToAppointmentView(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AppointmentView.fxml"));
        stage.setTitle("Appointment - View");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Saves user's input to database
     * @param actionEvent
     * @throws SQLException
     * @throws IOException
     */
    public void onSave(ActionEvent actionEvent) throws SQLException, IOException {
        if (emptyFields()) {
            txtDialogue.setText("Please fill out all fields");
        } else {
            txtDialogue.setText("");
            String title = txtTitle.getText();
            String description = txtDescription.getText();
            String location = txtLocation.getText();
            int contactID = ContactsDB.findContactID(comboContact.getSelectionModel().getSelectedItem().toString());
            String type = txtType.getText();
            LocalDate startDate = dateStart.getValue();
            LocalTime startTime = (LocalTime) comboStartTime.getSelectionModel().getSelectedItem();
            LocalDate endDate = dateEnd.getValue();
            LocalTime endTime = (LocalTime) comboEndTime.getSelectionModel().getSelectedItem();
            int customerID = CustomerDB.findCustID(comboCustomer.getSelectionModel().getSelectedItem().toString());
            int userID = UsersDB.findUserID(comboUserID.getSelectionModel().getSelectedItem().toString());

            LocalDateTime startUTC = LocalDateTime.of(startDate, startTime);
            LocalDateTime endUTC = LocalDateTime.of(endDate, endTime);

            if(!TimeManager.checkOverlap(startUTC, endUTC, customerID)) {
                AppointmentsDB.addAppointment(title, description, location, type, startUTC, endUTC, customerID, userID, contactID);
                onActionToAppointmentView(actionEvent);
            }else{
                txtDialogue.setText("Appointment conflict, please select a different time");
            }
        }
    }

    /** Checks if any of the fields are empty
     * @return
     */
    public boolean emptyFields() {
        boolean title = txtTitle.getText().isEmpty();
        boolean description = txtDescription.getText().isEmpty();
        boolean location = txtLocation.getText().isEmpty();
        boolean contact = comboContact.getSelectionModel().isEmpty();
        boolean type = txtType.getText().isEmpty();
        boolean startDate = dateStart.getValue() == null;
        boolean startTime = comboStartTime.getSelectionModel().isEmpty();
        boolean endDate = dateEnd.getValue() == null;
        boolean endTime = comboEndTime.getSelectionModel().isEmpty();
        boolean customer = comboCustomer.getSelectionModel().isEmpty();
        boolean user = comboUserID.getSelectionModel().isEmpty();


        return (title || description || location || contact || type || startDate || startTime || endDate || endTime || customer || user);
    }

    /** Initializes page by setting all comboboxes with appropriate values
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            comboContact.setItems(ContactsDB.getAllContacts());
            comboCustomer.setItems(CustomerDB.getAllCustomers());
            comboUserID.setItems(UsersDB.getAllUsers());
            comboStartTime.setItems(TimeManager.genUserBusinessHours());
            comboEndTime.setItems(TimeManager.genUserBusinessHours());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

