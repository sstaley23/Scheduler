package DB;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ReportsDB {


    /** Generates a list of RepMonthTypes
     * @return
     * @throws SQLException
     */
    public static ObservableList<MonthType> getMonthTypeAppts() throws SQLException {

        ObservableList<MonthType> monthTypeAppts = FXCollections.observableArrayList();

        String sql = "SELECT Year(Start) as Year, monthname(Start) as Month, Type, count(*) as Count "  +
                "FROM appointments " +
                "GROUP BY Year, Month, type";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            MonthType query = new MonthType(
                    rs.getString("Year"),
                    rs.getString("Month"),
                    rs.getString("Type"),
                    rs.getInt("Count"));
            monthTypeAppts.add(query);
        }
        return monthTypeAppts;
    }

    /** Generates a list of Months by year
     * @param year
     * @return
     * @throws SQLException
     */
    public static ObservableList<Month> getMonthsForYear(String year) throws SQLException {

        ObservableList<Month> months = FXCollections.observableArrayList();

        String sql = "SELECT distinct monthname(start) as month FROM appointments WHERE year(Start) = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, year);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Month query = new Month(
                    rs.getString("Month"));
            months.add(query);
        }
        return months;
    }

    /** Generates a list of types and counts of types for a given month and year
     * @param year
     * @param month
     * @return
     * @throws SQLException
     */
    public static ObservableList<TypeCount> filteredMonthTypeAppts(String year, String month) throws SQLException {

        ObservableList<TypeCount> list = FXCollections.observableArrayList();

        String sql = "SELECT DISTINCT Type, COUNT(Type) as Count " +
                "FROM appointments " +
                "WHERE YEAR(Start) = ? AND MONTHNAME(Start) = ? " +
                "GROUP BY Type";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, year);
        ps.setString(2, month);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            TypeCount query = new TypeCount(
                    rs.getString("Type"),
                    rs.getInt("Count"));
            list.add(query);
        }
        return list;
    }

    /** Generates a list of appointments for the Appointments by Contact reprot
     * @param contact
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointments> getContactAppointments(String contact) throws SQLException {
        ObservableList<Appointments> allAppointments = AppointmentsDB.getAllAppointments();
        ObservableList<Appointments> filteredAppointments = FXCollections.observableArrayList();

        for(Appointments a : allAppointments){
            if(contact.equals(a.getContactName())){
                filteredAppointments.add(a);
            }
        }
        return filteredAppointments;
    }

    /** Generates a list of customers by country
     * @param country
     * @return
     * @throws SQLException
     */
    public static ObservableList<Customers> getCustomersbyCountry(String country) throws SQLException {
        ObservableList<Customers> allCustomers = CustomerDB.getAllCustomers();
        ObservableList<Customers> filteredCustomers = FXCollections.observableArrayList();

        for(Customers c : allCustomers){
            if(country.equals(c.getCountry())){
                filteredCustomers.add(c);
            }
        }
        return filteredCustomers;
    }
}
