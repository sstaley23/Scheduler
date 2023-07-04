package Controller;

import DB.AppointmentsDB;
import DB.ContactsDB;
import DB.CustomerDB;
import DB.UsersDB;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import Model.Users;
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

/** Allows user to edit appointments <br>
 * Requirements satisfied in this Controller: A3a, A3d, A3c
 * */
public class AppointmentEdit implements Initializable {


    Stage stage;
    Parent scene;

    @FXML
    public TextField txtApptID;
    @FXML
    public TextField txtApptTitle;
    @FXML
    public TextField txtApptDescription;
    @FXML
    public TextField txtApptLocation;
    @FXML
    public TextField txtApptType;
    @FXML
    public DatePicker dpStartDate;
    @FXML
    public DatePicker dpEndDate;
    @FXML
    public ComboBox comboStartTime;
    @FXML
    public ComboBox comboEndtime;
    @FXML
    public ComboBox comboContact;
    @FXML
    public ComboBox comboCustomer;
    @FXML
    public ComboBox comboUser;
    @FXML
    public Label txtDialogue;

    /** Navigates to customer view menu
     * @param actionEvent
     * @throws IOException
     */
    public void onActionToAppointmentsView(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AppointmentView.fxml"));
        stage.setTitle("Appointments - View");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Populates fields from appointment view selection
     * @param appointment
     * @throws SQLException
     */
    public void sendAppointment(Appointments appointment) throws SQLException {
        Appointments selAppointment = appointment;
        txtApptID.setText(String.valueOf(selAppointment.getAppointmentID()));
        txtApptTitle.setText(String.valueOf(selAppointment.getTitle()));
        txtApptDescription.setText(String.valueOf(selAppointment.getDescription()));
        txtApptLocation.setText(String.valueOf(selAppointment.getLocation()));
        txtApptType.setText(String.valueOf(selAppointment.getType()));
        dpStartDate.setValue(selAppointment.getStartDate());
        dpEndDate.setValue(selAppointment.getEndDate());
        comboStartTime.getSelectionModel().select(selAppointment.getStartTime());
        comboEndtime.getSelectionModel().select(selAppointment.getEndTime());

        Contacts selContact = null;
        for(Contacts contact : ContactsDB.getAllContacts()) {
            if(contact.getContactName().equals(selAppointment.getContactName())){
                selContact = contact;
            }
        }
        comboContact.getSelectionModel().select(selContact);

        Customers selCustomer = null;
        for(Customers customer : CustomerDB.getAllCustomers()){
            if(customer.getId() == selAppointment.getCustomerID()){
                selCustomer = customer;
            }
        }
        comboCustomer.getSelectionModel().select(selCustomer);

        Users selUser = null;
        for(Users user : UsersDB.getAllUsers()){
            if(user.getUserID() == selAppointment.getUserID()){
                selUser = user;
            }
        }
        comboUser.getSelectionModel().select(selUser);

    }

    /** Saves updated appointment information
     * @param actionEvent
     * @throws SQLException
     * @throws IOException
     */
    public void onSave(ActionEvent actionEvent) throws SQLException, IOException {
        if (emptyFields()) {
            txtDialogue.setText("Please fill out all fields.");
        } else {
            txtDialogue.setText("");
            int apptID = Integer.parseInt(txtApptID.getText());
            String title = txtApptTitle.getText();
            String description = txtApptDescription.getText();
            String location = txtApptLocation.getText();
            int contactID = ContactsDB.findContactID(comboContact.getSelectionModel().getSelectedItem().toString());
            String type = txtApptType.getText();
            LocalDate startDate = dpStartDate.getValue();
            LocalTime startTime = (LocalTime) comboStartTime.getSelectionModel().getSelectedItem();
            LocalDate endDate = dpEndDate.getValue();
            LocalTime endTime = (LocalTime) comboEndtime.getSelectionModel().getSelectedItem();
            int customerID = CustomerDB.findCustID(comboCustomer.getSelectionModel().getSelectedItem().toString());
            int userID = UsersDB.findUserID(comboUser.getSelectionModel().getSelectedItem().toString());

            LocalDateTime startUTC = LocalDateTime.of(startDate, startTime);
            LocalDateTime endUTC = LocalDateTime.of(endDate, endTime);

            if(TimeManager.checkExist(customerID, apptID)){
                AppointmentsDB.upDateAppointment(apptID, title, description, location, type, startUTC, endUTC, customerID, userID, contactID);
                onActionToAppointmentsView(actionEvent);
            }else {
                if(!TimeManager.checkOverlap(startUTC, endUTC, customerID)){
                    AppointmentsDB.upDateAppointment(apptID, title, description, location, type, startUTC, endUTC, customerID, userID, contactID);
                    onActionToAppointmentsView(actionEvent);
                }else {
                    txtDialogue.setText("Appointment conflict, please select a different time");
                }
            }

        }
    }

    /** Checks if any of the fields are empty
     * @return
     */
    public boolean emptyFields() {
        boolean title = txtApptTitle.getText().isEmpty();
        boolean description = txtApptDescription.getText().isEmpty();
        boolean location = txtApptLocation.getText().isEmpty();
        boolean contact = comboContact.getSelectionModel().isEmpty();
        boolean type = txtApptType.getText().isEmpty();
        boolean startDate = dpStartDate.getValue() == null;
        boolean startTime = comboStartTime.getSelectionModel().isEmpty();
        boolean endDate = dpEndDate.getValue() == null;
        boolean endTime = comboEndtime.getSelectionModel().isEmpty();
        boolean customer = comboCustomer.getSelectionModel().isEmpty();
        boolean user = comboUser.getSelectionModel().isEmpty();


        return (title || description || location || contact || type || startDate || startTime || endDate || endTime || customer || user);
    }

    /** Initializes combo boxes with appropriate values
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            comboContact.setItems(ContactsDB.getAllContacts());
            comboCustomer.setItems(CustomerDB.getAllCustomers());
            comboUser.setItems(UsersDB.getAllUsers());
            comboStartTime.setItems(TimeManager.genUserBusinessHours());
            comboEndtime.setItems(TimeManager.genUserBusinessHours());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


}

