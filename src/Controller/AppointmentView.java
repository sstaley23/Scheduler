package Controller;

import DAO.AppointmentsDAO;
import Model.Appointments;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/** Appointment view class */
public class AppointmentView implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    public TableView<Appointments> tableviewAllAppointments;
    @FXML
    public TableColumn<Appointments, Integer> colAllAppointmentID;
    @FXML
    public TableColumn<Appointments, String> colAllTitle;
    @FXML
    public TableColumn<Appointments, String> colAllDescription;
    @FXML
    public TableColumn<Appointments, String> colAllLocation;
    @FXML
    public TableColumn<Appointments, String> colAllContact;
    @FXML
    public TableColumn<Appointments, String> colAllType;
    @FXML
    public TableColumn<Appointments, LocalDate> colAllStartDate;
    @FXML
    public TableColumn<Appointments, LocalTime> colAllStartTime;
    @FXML
    public TableColumn<Appointments, LocalDate> colAllEndDate;
    @FXML
    public TableColumn<Appointments, LocalTime> colAllEndTime;
    @FXML
    public TableColumn<Appointments, Integer> colAllCustomerID;
    @FXML
    public TableColumn<Appointments, Integer> colAllUserID;


    public void generateAllAppointmentsTable(ObservableList<Appointments> appointmentList) {
        tableviewAllAppointments.setItems(appointmentList);
        colAllAppointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        colAllTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAllDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colAllLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colAllContact.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        colAllType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colAllStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colAllStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colAllEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colAllEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colAllCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colAllUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }
    /** Method navigates back to the main menu
     * @param actionEvent
     * @throws IOException
     */
    public void onActionToMain(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setTitle("Main Menu");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Method navigates to the add appointment menu
     * @param actionEvent
     * @throws IOException
     */
    public void onActionToAddAppointment(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AppointmentAdd.fxml"));
        stage.setTitle("Appointment - Add");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Method navigates to the edit appointment menu
     * @param actionEvent
     * @throws IOException
     */
    public void onActionToEditAppointment(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AppointmentEdit.fxml"));
        stage.setTitle("Appointment - Edit");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            generateAllAppointmentsTable(AppointmentsDAO.getAllAppointments());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

