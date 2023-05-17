package Controller;

import Model.Country;
import Model.Division;
import Utilities.CountryAndDivisionQuery;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Filter;

/** Allows user to add customers */
public class CustomerAdd implements Initializable {

    public static int onCountryCombo;
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

    public void onCountryCombo(ActionEvent actionEvent) throws SQLException {
        int selectionNumber = 0;
        //review
        ObservableList<Division> Filterdivisions = FXCollections.observableArrayList();
        //.equal lookup
        if (comboCountry.getValue().toString().equals("U.S")) {
            selectionNumber = 1;
        }

        if (comboCountry.getValue().toString().equals("UK")) {
            selectionNumber = 2;
        }

        if (comboCountry.getValue().toString().equals("Canada")) {
            selectionNumber = 3;
        }

        if (selectionNumber == 0) {
            //error
        } else {
            for (Division d : CountryAndDivisionQuery.getDivisions()) {

                if (selectionNumber == d.getCountryID()) {
                    Filterdivisions.add(d);
                }
            }
            comboDivision.setItems(Filterdivisions);
        }

    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            comboCountry.setItems(CountryAndDivisionQuery.getAllCountries());
//            comboDivision.setItems(CountryAndDivisionQuery.getDivisions());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}

