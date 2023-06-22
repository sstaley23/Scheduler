package Controller;

import DB.CountryDB;
import DB.CustomerDB;
import DB.DivisionDB;
import Model.Country;
import Model.Customers;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/** Allows user to edit customers */
public class CustomerEdit {


    Stage stage;
    Parent scene;

    @FXML
    public TextField txtID;
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtAddress;
    @FXML
    public TextField txtPostal;
    @FXML
    public TextField txtPhone;
    @FXML
    public ComboBox<Country> comboCountry;
    @FXML
    public ComboBox<Division> comboDivision;
    @FXML
    public Label txtDialogue;

    private int selectionNumber;


    /** Populates the text fields with the appropriate data
     * @param customer
     * @throws SQLException
     */
    @FXML
    public void sendCustomer(Customers customer) throws SQLException {

        Customers selCustomer = customer;
        txtID.setText(String.valueOf(customer.getId()));
        txtName.setText(String.valueOf(customer.getName()));
        txtAddress.setText(String.valueOf(customer.getAddress()));
        txtPostal.setText(String.valueOf(customer.getPostalCode()));
        txtPhone.setText(String.valueOf(customer.getPhoneNumber()));

        Country selCountry = null;
        for(Country country : CountryDB.getAllCountries()) {
            if(country.getCountry().equals(selCustomer.getCountry())) {
                selCountry = country;
            }
        }
        comboCountry.getSelectionModel().select(selCountry);

        Division selDivision = null;
        for(Division division : DivisionDB.getAllDivisions()) {
            if(division.getDivision().equals(selCustomer.getFirstLevel())) {
                selDivision = division;
            }
        }
        comboDivision.getSelectionModel().select(selDivision);


    }

    /** Populates the drop down menu of the country combo box on click
     * @param mouseEvent
     * @throws SQLException
     */
    public void onClickCountry(MouseEvent mouseEvent) throws SQLException {
        comboCountry.setItems(CountryDB.getAllCountries());
    }

    /** Populates the drop down menu with filtered results for the division combo box
     * @param actionEvent
     * @return
     * @throws SQLException
     */
    public int onCountryCombo(ActionEvent actionEvent) throws SQLException {

        selectionNumber = 0;
        ObservableList<Division> Filterdivisions = FXCollections.observableArrayList();

        if (comboCountry.getValue().toString().equals("U.S")) {
            selectionNumber = 1;
        }

        if (comboCountry.getValue().toString().equals("UK")) {
            selectionNumber = 2;
        }

        if (comboCountry.getValue().toString().equals("Canada")) {
            selectionNumber = 3;
        }

        if (selectionNumber != 0) {
            for (Division d : DivisionDB.getAllDivisions()) {
                if (selectionNumber == d.getCountryID()) {
                    Filterdivisions.add(d);
                }
            }
            comboDivision.setItems(Filterdivisions);
            //Added select first ; clear button for text fields only
            comboDivision.getSelectionModel().selectFirst();
        }
        return selectionNumber;
    }

    /** Generates error messages if the division is selected first
     * @param mouseEvent
     */
    public void onClickDivision(MouseEvent mouseEvent) {
        if(selectionNumber == 0) {
            System.out.println(selectionNumber);
            comboDivision.setPromptText("You Must Choose A Country First...");
            txtDialogue.setText("Please select Country First.");
        } else {
            txtDialogue.setText("");
        };
    }


    /** Saves updated customer information
     * @param actionEvent
     */
    public void onSave(ActionEvent actionEvent) {
        if(emptyFields()) {
            txtDialogue.setText("Please fill out all fields.");
        } else {
            int id = Integer.parseInt(txtID.getText());
            String name = txtName.getText();
            String address = txtAddress.getText();
            String postal = txtPostal.getText();
            String phone = txtPhone.getText();
            int divisionID = comboDivision.getValue().getDivisionID();
            emptyFields();
            try {
                CustomerDB.upDateCustomer(id, name, address, postal, phone, divisionID);
                onActionToCustomerView(actionEvent);
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /** Verifies no empty fields exist on save
     * @return
     */
    public boolean emptyFields(){
        boolean name = txtName.getText().isEmpty();
        boolean address = txtAddress.getText().isEmpty();
        boolean postal = txtPostal.getText().isEmpty();
        boolean phone = txtPhone.getText().isEmpty();
        boolean country = comboCountry.getSelectionModel().getSelectedItem().toString().isBlank();
        boolean division = comboDivision.getSelectionModel().getSelectedItem().toString().isBlank();

        return (name || address || postal || phone || country || division);
    }

     /** Method navigates back to customer view
     * @param actionEvent
     * @throws IOException
     */
    public void onActionToCustomerView(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerView.fxml"));
        stage.setTitle("Customers - View");
        stage.setScene(new Scene(scene));
        stage.show();
    }



}

