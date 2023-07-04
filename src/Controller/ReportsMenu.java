package Controller;

import DB.ReportsDB;
import Model.MonthType;
import Model.TypeCount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    @FXML
    public Label txtDialogue;
    @FXML
    public ComboBox atcComboMonth;
    @FXML
    public TableView<TypeCount> atcTableView;
    @FXML
    public TableColumn<TypeCount, String> atcColType;
    @FXML
    public TableColumn<TypeCount, Integer> atcColCount;


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

        for(MonthType r : ReportsDB.getMonthTypeAppts()){
            if(!year.contains(r.getYear())){
                year.add(r.getYear());
            }
        }
        return year;
    }

    /**Generates a list of months after a year is selected
     * @param mouseEvent
     * @throws SQLException
     */
    public void onClickMonth(MouseEvent mouseEvent) throws SQLException {
        if(atcComboYear.getSelectionModel().isEmpty()){
            txtDialogue.setText("Please select a year first...");
        }else{
            String year = atcComboYear.getValue().toString();
            atcComboMonth.setItems(ReportsDB.getMonthsForYear(year));
        }
    }

    /**Generates the table view for the ATC table
     * @param list
     */
    public void genATCTable(ObservableList<TypeCount> list){
        atcTableView.setItems(list);
        atcColType.setCellValueFactory(new PropertyValueFactory<>("type"));
        atcColCount.setCellValueFactory(new PropertyValueFactory<>("count"));
    }

    /**Verifies that the year/month combos are not empty and calls genATCTable if not
     * @param actionEvent
     * @throws SQLException
     */
    public void atcOnSelect(ActionEvent actionEvent) throws SQLException {
        Boolean ifYear = atcComboYear.getSelectionModel().isEmpty();
        Boolean ifMonth = atcComboMonth.getSelectionModel().isEmpty();

        if(ifYear || ifMonth){
            txtDialogue.setText("Please select values.");
        }else {
            txtDialogue.setText("");

            String year = atcComboYear.getValue().toString();
            String month = atcComboMonth.getValue().toString();

            genATCTable(ReportsDB.filteredMonthTypeAppts(year, month));
        }
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
