package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/** This class allows users with credentials to navigate to the scheduling app */
public class LoginMenu {

    Stage stage;
    Parent scene;

    /** Method navigates to the Main Menu
     * @param actionEvent user interaction with the login button
     * @throws IOException
     * */
    @FXML
    public void onActionMainMenu(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setTitle("Main Menu");
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
