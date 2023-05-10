package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/** Appointment view class */
public class AppointmentView {
    Stage stage;
    Parent scene;

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
}

