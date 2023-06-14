package Controller;

import DAO.ContactsDAO;
import DAO.CustomerDAO;
import DAO.UsersDAO;
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
import java.util.ResourceBundle;

/** Allows user to add appointments */
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
    public ComboBox comboCustomerID;
    @FXML
    public ComboBox comboUserID;


    /** Method navigates back to the customer view
     * @param actionEvent
     * @throws IOException
     */
    public void onActionToCustomerView(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AppointmentView.fxml"));
        stage.setTitle("Appointment - View");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            comboContact.setItems(ContactsDAO.getAllContacts());
            comboCustomerID.setItems(CustomerDAO.getAllCustomers());
            comboUserID.setItems(UsersDAO.getAllUsers());
            comboStartTime.setItems(TimeManager.genUserBusinessHours());
            comboEndTime.setItems(TimeManager.genUserBusinessHours());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

