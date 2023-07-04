package Controller;

import DB.ContactsDB;
import DB.CountryDB;
import DB.ReportsDB;
import Model.Appointments;
import Model.Customers;
import Model.MonthType;
import Model.TypeCount;
import javafx.collections.FXCollections;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

/** Controller generates reports for requirement A3f and includes the lambda expressions for requirement B
 */
public class ReportsMenu implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    public ComboBox atcComboYear;
    @FXML
    public Label atcTxtDialogue;
    @FXML
    public ComboBox atcComboMonth;
    @FXML
    public TableView<TypeCount> atcTableView;
    @FXML
    public TableColumn<TypeCount, String> atcColType;
    @FXML
    public TableColumn<TypeCount, Integer> atcColCount;
    @FXML
    public TableView<Appointments> abcTableView;
    @FXML
    public TableColumn<Appointments, Integer> abcColApptID;
    @FXML
    public TableColumn<Appointments, String> abcColTitle;
    @FXML
    public TableColumn<Appointments, String> abcColType;
    @FXML
    public TableColumn<Appointments, String> abcColDescription;
    @FXML
    public TableColumn<Appointments, LocalDate> abcColStartDate;
    @FXML
    public TableColumn<Appointments, LocalTime> abcColStartTime;
    @FXML
    public TableColumn<Appointments, LocalDate> abcColEndDate;
    @FXML
    public TableColumn<Appointments, LocalTime> abcColEndTime;
    @FXML
    public TableColumn<Appointments, Integer> abcColCustomerID;
    @FXML
    public ComboBox abcContactCombo;
    @FXML
    public Label abcTxtDialogue;
    @FXML
    public ComboBox cbcComboCountry;
    @FXML
    public TableView<Customers> cbcTableView;
    @FXML
    public TableColumn<Customers, Integer> cbcColCustomerID;
    @FXML
    public TableColumn<Customers, String> cbcColCustomerName;
    @FXML
    public TableColumn<Customers, String> cbcColAddress;
    @FXML
    public TableColumn<Customers, String> cbcColPostalCode;
    @FXML
    public TableColumn<Customers, String> cbcColPhone;
    @FXML
    public TableColumn<Customers, String> cbcColDivision;
    @FXML
    public Label cbcTxtDialogue;

/*--- Navigation ---*/
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

/*--- ATC Tab specific ---*/

    /** Clears combos and tables when navigating back to the tabe
     * @param event
     * @throws SQLException
     */
    public void onTabATC(Event event) throws SQLException {
        atcTableView.getItems().clear();
        atcComboYear.getSelectionModel().clearSelection();
        atcComboMonth.getSelectionModel().clearSelection();
        atcComboYear.setItems(genyear());
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
            atcTxtDialogue.setText("Please select a year first...");
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
            atcTxtDialogue.setText("Please select values.");
        }else {
            atcTxtDialogue.setText("");

            String year = atcComboYear.getValue().toString();
            String month = atcComboMonth.getValue().toString();

            genATCTable(ReportsDB.filteredMonthTypeAppts(year, month));
        }
    }

/*--- ABC Tab Specific ---*/

    /** Clear combo/tableview when navigating back to ABC Tab and generates the combo with data
     * @param event
     * @throws SQLException
     */
    public void onTabABC(Event event) throws SQLException {
        abcTableView.getItems().clear();
        abcContactCombo.getSelectionModel().clearSelection();
        abcContactCombo.setItems(ContactsDB.getAllContacts());
    }

    /**Generates the Appointments by Contact table
     * @param list
     */
    public void genABCTable(ObservableList<Appointments> list){
        abcTableView.setItems(list);
        abcColApptID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        abcColTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        abcColDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        abcColType.setCellValueFactory(new PropertyValueFactory<>("type"));
        abcColStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        abcColStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        abcColEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        abcColEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        abcColCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    }

    /*--- CBC Tab Specific ---*/

    /** Clear combo/tableview when navigating back to CBC tabd and generates combo with data
     * @param event
     * @throws SQLException
     */
    public void onTabCBC(Event event) throws SQLException {
        cbcTableView.getItems().clear();
        cbcComboCountry.getSelectionModel().clearSelection();
        cbcComboCountry.setItems(CountryDB.getAllCountries());
    }

    /**Generates the Customers by country table
     * @param list
     */
    public void genCBCTable(ObservableList<Customers> list){
        cbcTableView.setItems(list);
        cbcColCustomerID.setCellValueFactory(new PropertyValueFactory<>("id"));
        cbcColCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cbcColAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        cbcColPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        cbcColPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        cbcColDivision.setCellValueFactory(new PropertyValueFactory<>("firstLevel"));
    }

    /**Calls genCBCTable if country combo is not empty
     * @param actionEvent
     * @throws SQLException
     */
    public void cbcOnSelect(ActionEvent actionEvent) throws SQLException {
        Boolean ifCountry = cbcComboCountry.getSelectionModel().isEmpty();

        if(ifCountry){
            cbcTxtDialogue.setText("Please select a country");
        }else {
            cbcTxtDialogue.setText("");

            String country = cbcComboCountry.getValue().toString();
            genCBCTable(ReportsDB.getCustomersbyCountry(country));
        }
    }

    /** Below initializes the atcComboYear when loading the default, provides a Lambda Expression (Lamda ATC) for generating the atcMonthCombo ,and provides a Lambda Expression (Lamda ABC) for the abcContact Combo <br>
     * <br>
     * Lambda ATC was chosen to clean up the code for the generation of the atcMonthCombo based off the selection of the atcYearCombo
     *  This action allowed for more stream lined code for the onClickMonth method. <br>
     * <br>
     * Lambda ABC allowed for a cleaner UI and code by removing the need of a "Select" button and onSelect method.
     *  With this action, the table view automatically generates when a contact is selected
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            atcComboYear.setItems(genyear());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        /*Lambda ATC*/
        atcComboYear.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if(newValue != null){
                atcTxtDialogue.setText("");

                String year = atcComboYear.getValue().toString();
                try {
                    atcComboMonth.setItems(ReportsDB.getMonthsForYear(year));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        /*Lambda ABC*/
        abcContactCombo.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if(newValue != null){
                abcTxtDialogue.setText("");

                String contact = abcContactCombo.getValue().toString();
                try {
                    genABCTable(ReportsDB.getContactAppointments(contact));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

    }


}
