package Controller;

import DB.AppointmentsDB;
import Model.Appointments;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

/** Appointment view class <br>
 * * Requirements satisfied in this Controller: A3b
 * */
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
    @FXML
    public Label txtDialogue;

    int currentView = 0;


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
    public void onActionToEditAppointment(ActionEvent actionEvent) throws IOException, SQLException {
        Appointments selAppt;

        if(currentView == 2){
            selAppt = tableviewWKAppointments.getSelectionModel().getSelectedItem();
        }else if(currentView == 1){
            selAppt = tableviewMOAppointments.getSelectionModel().getSelectedItem();
        }else {
            selAppt = tableviewAllAppointments.getSelectionModel().getSelectedItem();
        }

        if(selAppt != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/AppointmentEdit.fxml"));
            loader.load();

            AppointmentEdit MDEditController = loader.getController();
            MDEditController.sendAppointment(selAppt);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setTitle("Appointment - Edit");
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            txtDialogue.setText("Please select appointment.");
        }

    }

    /** Method generates the appointments for the month when the tab is selected
     * @param event
     * @throws SQLException
     */
    public void onselThisMonth(Event event) throws SQLException {

        ObservableList monthList = AppointmentsDB.getAppointmentsForMonth(LocalDate.now());
        currentView = 1;

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

    /** Method generates the appointments for the week when the tab is selected
     * @param event
     * @throws SQLException
     */
    public void onselThisWeek(Event event) throws SQLException {
        ObservableList monthList = AppointmentsDB.getAppointmentsForWeek(LocalDate.now());
        currentView = 2;

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

    /** Method regenerates the all appointments table view
     * This was created to fix selection issues during delete/edit
     * @param event
     * @throws SQLException
     */
    public void onselAllAppointments(Event event) throws SQLException {
        generateAllAppointmentsTable(AppointmentsDB.getAllAppointments());
        currentView = 0;
    }

    /** Deletes selected appointment
     * @param actionEvent
     * @throws SQLException
     */
    public void onDelete(ActionEvent actionEvent) throws SQLException {
        Appointments delAppt;

        if(currentView == 2){
            delAppt = tableviewWKAppointments.getSelectionModel().getSelectedItem();
        }else if(currentView == 1){
            delAppt = tableviewMOAppointments.getSelectionModel().getSelectedItem();
        } else {
            delAppt = tableviewAllAppointments.getSelectionModel().getSelectedItem();
        }
        int id;
        String type;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this appointment?");


        if(delAppt != null){
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                if(AppointmentsDB.deleteAppointment(delAppt.getAppointmentID()) != 0){
                    if(currentView == 2){
                        tableviewWKAppointments.getItems().remove(delAppt);
                    } else if(currentView == 1){
                        tableviewMOAppointments.getItems().remove(delAppt);
                    }else {
                        tableviewAllAppointments.getItems().remove(delAppt);
                    }
                                        id = delAppt.getAppointmentID();
                    type = delAppt.getType();
                    txtDialogue.setText("Appointment ID: " + id + " Type: " + type + " deleted");
                }
            }
        } else {
            txtDialogue.setText("Please select an appointment.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            generateAllAppointmentsTable(AppointmentsDB.getAllAppointments());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}

