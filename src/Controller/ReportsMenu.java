package Controller;

import DB.ReportsDB;
import Model.repMonthType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReportsMenu implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    public ComboBox atcComboYear;

    /**Navigates back to the main menu
     * @param actionEvent
     * @throws IOException
     */
    public void onMain(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setTitle("Main Menu");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Generate a list of years found in database
     * @return
     * @throws SQLException
     */
    public ObservableList genyear() throws SQLException {
        ObservableList<String> year = FXCollections.observableArrayList();

        for(repMonthType r : ReportsDB.getMonthTypeAppts()){
            if(!year.contains(r.getYear())){
                year.add(r.getYear());
            }
        }
        return year;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            atcComboYear.setItems(genyear());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
