package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/** Allows user to edit appointments */
public class AppointmentEdit {

    Stage stage;
    Parent scene;


    public void onActionToCustomerView(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AppointmentView.fxml"));
        stage.setTitle("Appointments - View");
        stage.setScene(new Scene(scene));
        stage.show();
    }
}

