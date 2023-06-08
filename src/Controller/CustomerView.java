package Controller;

import DAO.CustomerDAO;
import Model.Customers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** Customer view menu. Navigates to Add, Edit, or back to main */
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
    public Label lblError;


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
            lblError.setText("Please select customer.");
        }

    }

    //Need to finish
    public void onDelete(ActionEvent actionEvent) {
        Customers customerDelete = tableviewCustomers.getSelectionModel().getSelectedItem();
    }

    /** Method initializes the class
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            generateCustomerTable(CustomerDAO.getAllCustomers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}
