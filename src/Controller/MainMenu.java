package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** Main menu that allows navigation to Customers View, Appointment View, and Reprots */
public class MainMenu {

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
}
