package Controller;

import DB.AppointmentsDB;
import DB.CustomerDB;
import Model.Customers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.Optional;
import java.util.ResourceBundle;

/** Customer view menu. Navigates to Add, Edit, or back to main
 * This controller satisfies some of requirement A2
 */
public class CustomerView implements Initializable {


    Stage stage;
    Parent scene;

    @FXML
    public TableView<Customers> tableviewCustomers;
    @FXML
    public TableColumn<Customers, Integer> colCustomerID;
    @FXML
    public TableColumn<Customers, String> colCustomerName;
    @FXML
    public TableColumn<Customers, String> colCustomerAddress;
    @FXML
    public TableColumn<Customers, String> colCustomerFirstLevel;
    @FXML
    public TableColumn<Customers, String> colCustomerCountry;
    @FXML
    public TableColumn<Customers, String> colCustomerPostal;
    @FXML
    public TableColumn<Customers, String> colCustomerPhone;
    @FXML
    public Label txtDialogue;


    /** Method generates customer table view
      * @param customerList
     */
    public void generateCustomerTable(ObservableList<Customers> customerList) {
        tableviewCustomers.setItems(customerList);
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCustomerFirstLevel.setCellValueFactory(new PropertyValueFactory<>("firstLevel"));
        colCustomerCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        colCustomerPostal.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        colCustomerPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }

    /** Method navigates back to main menu
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

    /** Method navigates to the customer add form
     * @param actionEvent
     * @throws IOException
     */
    public void onActionToAddCustomer(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerAdd.fxml"));
        stage.setTitle("Customer - Add");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Method navigates to the customer edit form
     * @param actionEvent
     * @throws IOException
     */
    public void onActionToEditCustomer(ActionEvent actionEvent) throws IOException, SQLException {

        Customers selCustomer = tableviewCustomers.getSelectionModel().getSelectedItem();

        if (selCustomer != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/CustomerEdit.fxml"));
            loader.load();

            CustomerEdit MDEditController = loader.getController();
            MDEditController.sendCustomer(selCustomer);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setTitle("Customer - Edit");
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            txtDialogue.setText("Please select customer.");
        }

    }

    /** Deletes customer from database if no appointmetns exist
     * @param actionEvent
     * @throws SQLException
     * @throws IOException
     */
    public void onDelete(ActionEvent actionEvent) throws SQLException, IOException {
        Customers customerDelete = tableviewCustomers.getSelectionModel().getSelectedItem();
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this customer?");

        if(customerDelete != null){
            int customerID = customerDelete.getId();
            int numAppointments = AppointmentsDB.countAppointments(customerID);
            Optional<ButtonType> result1 = alert1.showAndWait();
            if(result1.isPresent() && result1.get() == ButtonType.OK){
                if(numAppointments == 0){
                    CustomerDB.deleteCustomer(customerID);
                    tableviewCustomers.getItems().remove(customerDelete);
                    txtDialogue.setText("Customer " + customerID + " has been removed.");
                }else {
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "This customer has " + numAppointments + " appointments that must be deleted/reassigned first.\n\n" +
                            "Would you like to navigate to the Appointments Table?");
                    Optional<ButtonType> result2 = alert2.showAndWait();
                    if(result2.isPresent() && result2.get() == ButtonType.OK){
                        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("/View/AppointmentView.fxml"));
                        stage.setTitle("Appointments - View");
                        stage.setScene(new Scene(scene));
                        stage.show();
                    }
                }
            }
        } else {
            txtDialogue.setText("Please select customer.");
        }

    }

    /** Method initializes the class
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            generateCustomerTable(CustomerDB.getAllCustomers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}
