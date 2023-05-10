package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/** Allows user to add appointments */
public class AppointmentAdd {

    Stage stage;
    Parent scene;

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
}

