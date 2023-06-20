package Controller;

import DAO.ContactsDAO;
import DAO.CustomerDAO;
import DAO.UsersDAO;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.util.ResourceBundle;

/** Allows user to edit appointments */
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

    /** Navigates to customer view menu
     * @param actionEvent
     * @throws IOException
     */
    public void onActionToCustomerView(ActionEvent actionEvent) throws IOException {
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
        for(Contacts contact : ContactsDAO.getAllContacts()) {
            if(contact.getContactName().equals(selAppointment.getContactName())){
                selContact = contact;
            }
        }
        comboContact.getSelectionModel().select(selContact);

        Customers selCustomer = null;
        for(Customers customer : CustomerDAO.getAllCustomers()){
            if(customer.getId() == selAppointment.getCustomerID()){
                selCustomer = customer;
            }
        }
        comboCustomer.getSelectionModel().select(selCustomer);

        Users selUser = null;
        for(Users user : UsersDAO.getAllUsers()){
            if(user.getUserID() == selAppointment.getUserID()){
                selUser = user;
            }
        }
        comboUser.getSelectionModel().select(selUser);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboStartTime.setItems(TimeManager.genUserBusinessHours());
        comboEndtime.setItems(TimeManager.genUserBusinessHours());
    }
}

