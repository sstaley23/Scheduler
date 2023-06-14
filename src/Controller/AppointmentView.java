package Controller;

import DAO.AppointmentsDAO;
import Model.Appointments;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
    @FXML
    public TableView<Appointments> tableviewMOAppointments;
    @FXML
    public TableColumn<Appointments, Integer> colMOAppointmentID;
    @FXML
    public TableColumn<Appointments, String> colMOTitle;
    @FXML
    public TableColumn<Appointments, String> colMODescription;
    @FXML
    public TableColumn<Appointments, String> colMOLocation;
    @FXML
    public TableColumn<Appointments, String> colMOContact;
    @FXML
    public TableColumn<Appointments, String> colMOType;
    @FXML
    public TableColumn<Appointments, LocalDate> colMOStartDate;
    @FXML
    public TableColumn<Appointments, LocalTime> colMOStartTime;
    @FXML
    public TableColumn<Appointments, LocalDate> colMOEndDate;
    @FXML
    public TableColumn<Appointments, LocalTime> colMOEndTime;
    @FXML
    public TableColumn<Appointments, Integer> colMOCustomerID;
    @FXML
    public TableColumn<Appointments, Integer> colMOUserID;
    @FXML
    public TableView<Appointments> tableviewWKAppointments;
    @FXML
    public TableColumn<Appointments, Integer> colWKAppointmentID;
    @FXML
    public TableColumn<Appointments, String> colWKTitle;
    @FXML
    public TableColumn<Appointments, String> colWKDescription;
    @FXML
    public TableColumn<Appointments, String> colWKLocation;
    @FXML
    public TableColumn<Appointments, String> colWKContact;
    @FXML
    public TableColumn<Appointments, String> colWKType;
    @FXML
    public TableColumn<Appointments, LocalDate> colWKStartDate;
    @FXML
    public TableColumn<Appointments, LocalTime> colWKStartTime;
    @FXML
    public TableColumn<Appointments, LocalDate> colWKEndDate;
    @FXML
    public TableColumn<Appointments, LocalTime> colWKEndTime;
    @FXML
    public TableColumn<Appointments, Integer> colWKCustomerID;
    @FXML
    public TableColumn<Appointments, Integer> colWKUserID;


    /** Method generates the all appointments table
     * @param appointmentList
     */
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

    /** Method generates the appointments for the month when the tab is selected
     * @param event
     * @throws SQLException
     */
    public void onselThisMonth(Event event) throws SQLException {

        ObservableList monthList = AppointmentsDAO.getAppointmentsForMonth(LocalDate.now());

        tableviewMOAppointments.setItems(monthList);
        colMOAppointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        colMOTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMODescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colMOLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colMOContact.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        colMOType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colMOStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colMOStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colMOEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colMOEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colMOCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colMOUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }

    /** Method generates the appointmetns for the week when the tabe is selected
     * @param event
     * @throws SQLException
     */
    public void onselThisWeek(Event event) throws SQLException {
        ObservableList monthList = AppointmentsDAO.getAppointmentsForWeek(LocalDate.now());

        tableviewWKAppointments.setItems(monthList);
        colWKAppointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        colWKTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colWKDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colWKLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colWKContact.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        colWKType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colWKStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colWKStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colWKEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colWKEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colWKCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colWKUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
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

