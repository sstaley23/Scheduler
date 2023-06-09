package Controller;

import DB.AppointmentsDB;
import Model.Appointments;
import Utilities.TimeManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/** Main menu that allows navigation to Customers View, Appointment View, and Reports <BR>
 * Requirements satisfied in this Controller: A3e
 */
public class MainMenu implements Initializable {

    Stage stage;
    Parent scene;

    /** Method navigates to the Customer View menu
     * @param actionEvent
     * @throws IOException
     */
    public void onActionToCustomersView(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerView.fxml"));
        stage.setTitle("Customers - View");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Method navigates to the Appointment view menu
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

    /** Method navigates to the reports menu
     * @param actionEvent
     * @throws IOException
     */
    public void onActionToReports(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/ReportsMenu.fxml"));
        stage.setTitle("Main Menu");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Provides an alert when the exit button is clicked and exits program if ok is selected
     * @param actionEvent
     * @throws SQLException
     */
    public void onActionExit(ActionEvent actionEvent) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }

    }

    /** Provides appointment alerts (or lack there of) on initialization
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            boolean found = false;
            for(Appointments appt : AppointmentsDB.getAllAppointments()){
                if(TimeManager.glLogin.isBefore(appt.getStartDateTime()) && TimeManager.glLogin.isAfter(appt.getStartDateTime().minusMinutes(15))){
                    found = true;

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Appointment Reminder");
                    alert.setHeaderText(null);
                    alert.setContentText("You have an appointment coming up!\nAppointment ID: " + appt.getAppointmentID() + " Date: " + appt.getStartDate() + " Time: " + appt.getStartTime());

                    alert.showAndWait();
                }
            }

            if(!found){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment Reminder");
                alert.setHeaderText(null);
                alert.setContentText("You have no upcoming appointments");

                alert.showAndWait();
            }
            System.out.println(TimeManager.glLogin);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
