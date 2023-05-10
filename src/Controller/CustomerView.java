package Controller;

import Model.Customers;
import Utilities.CustomersQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    /** Method navigates to the customer add menu
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

    public void onActionToEditCustomer(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerEdit.fxml"));
        stage.setTitle("Customer - Edit");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            generateCustomerTable(CustomersQuery.getAllCustomers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
