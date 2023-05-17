package Utilities;

import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CustomersQuery {

    private static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();



    public static ObservableList<Customers> getAllCustomers() throws SQLException {
        allCustomers.clear();

        String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, first_level_divisions.Division, countries.Country, customers.Postal_Code, customers.Phone FROM customers, first_level_divisions, countries WHERE customers.Division_ID = first_level_divisions.Division_ID AND first_level_divisions.Country_ID = countries.Country_ID";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Customers customers = new Customers(
                    rs.getInt("Customer_ID"),
                    rs.getString("Customer_Name"),
                    rs.getString("Address"),
                    rs.getString("Division"),
                    rs.getString("Country"),
                    rs.getString("Postal_Code"),
                    rs.getString("Phone"));
            allCustomers.add(customers);
        }
        return allCustomers;
    }




}
