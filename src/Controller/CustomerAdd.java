package Controller;

import Model.Country;
import Model.Division;
import Utilities.CountryAndDivisionQuery;
import Utilities.CustomersQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Filter;

/** Allows user to add customers */
public class CustomerAdd implements Initializable {


    Stage stage;
    Parent scene;

    @FXML
    public TextField txtID;
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtAddress;
    @FXML
    public TextField txtPostalCode;
    @FXML
    public TextField txtPhone;
    @FXML
    public ComboBox<Country> comboCountry;
    @FXML
    public ComboBox<Division> comboDivision;
    @FXML
    public Label txtDialogue;

    private int selectionNumber;



    /**
     * Method navigates back to customer view
     *
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

    /** Method generates division combo box based on country combo box
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
             for (Division d : CountryAndDivisionQuery.getDivisions()) {

                if (selectionNumber == d.getCountryID()) {
                    Filterdivisions.add(d);
                }
            }
            comboDivision.setItems(Filterdivisions);
        }
        return selectionNumber;
    }

    /**Method generates and error in division combo box and error dialogue if country combo box is empty
     * @param mouseEvent
     */
    public void onClickDivisionCombo(MouseEvent mouseEvent) {
        if(selectionNumber == 0) {
            comboDivision.setPromptText("You Must Choose A Country First...");
            txtDialogue.setText("Please select Country First.");
        };
    }

    /** Method clears text fields and comboboxes
     * @param actionEvent
     */
    public void onClear(ActionEvent actionEvent) {
        txtName.clear();
        txtAddress.clear();
        comboCountry.getSelectionModel().clearSelection();
        comboDivision.getSelectionModel().clearSelection();
        txtPostalCode.clear();
        txtPhone.clear();
    }

    /** Method saves customer after checking for empty fields
     * @param actionEvent
     * @throws IOException
     */
    public void onSave(ActionEvent actionEvent) throws IOException {
        if(emptyFields()) {
            txtDialogue.setText("Please fill out all fields.");
        } else {
            String name = txtName.getText();
            String address = txtAddress.getText();
            String postal = txtPostalCode.getText();
            String phone = txtPhone.getText();
            int divisionID = comboDivision.getValue().getDivisionID();
            emptyFields();
            try {
                CustomersQuery.addCustomer(name, address, postal, phone, divisionID);
                onActionToCustomerView(actionEvent);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /** Method checks for empty fields
     * @return boolean (true if a field is empty)
     */
    public boolean emptyFields(){
        boolean name = txtName.getText().isEmpty();
        boolean address = txtAddress.getText().isEmpty();
        boolean postal = txtPostalCode.getText().isEmpty();
        boolean phone = txtPhone.getText().isEmpty();
        boolean country = comboCountry.getSelectionModel().isEmpty();
        boolean division = comboDivision.getSelectionModel().isEmpty();

        return (name || address || postal || phone || country || division);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            comboCountry.setItems(CountryAndDivisionQuery.getAllCountries());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}

