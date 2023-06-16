package DAO;

import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CustomerDAO {

   public static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    /** Generates a list of all customers from database
     * @return
     * @throws SQLException
     */
    public static ObservableList<Customers> getAllCustomers() throws SQLException {
        allCustomers.clear();

        String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, first_level_divisions.Division, countries.Country, customers.Postal_Code, customers.Phone "
                + "FROM customers, first_level_divisions, countries "
                + "WHERE customers.Division_ID = first_level_divisions.Division_ID AND first_level_divisions.Country_ID = countries.Country_ID";
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

    /** Adds new customer to database
     * @param name
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionID
     * @return
     * @throws SQLException
     */
    public static int addCustomer(String name, String address, String postalCode, String phone, int divisionID) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divisionID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** Updates customer in database
     * @param id
     * @param name
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionID
     * @return
     * @throws SQLException
     */
    public static  int upDateCustomer(int id, String name, String address, String postalCode, String phone, int divisionID) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divisionID);
        ps.setInt(6, id);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /** Removes customer from database
     * @param customerID
     * @return
     * @throws SQLException
     */
    public static int deleteCustomer(int customerID) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerID);
        int rowAffected = ps.executeUpdate();
        return rowAffected;
    }

    /** Finds customer's id when given a name
     * @param name
     * @return
     * @throws SQLException
     */
    public static int findCustID(String name) throws SQLException {
        int id = -1;
        ObservableList<Customers> customers = getAllCustomers();

        for(Customers cust : customers){
            if(name.equals(cust.getName())){
                id = cust.getId();
                break;
            }
        }
        return id;
    }
}
