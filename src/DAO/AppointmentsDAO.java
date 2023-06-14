package DAO;

import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;

public abstract class AppointmentsDAO {

    public static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
    public static ObservableList<Appointments> filteredAppointments = FXCollections.observableArrayList();
    public static LocalDate currentDate;

    /** Retrieves all appointments from database
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointments> getAllAppointments() throws SQLException {
        allAppointments.clear();
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;

        String sql = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, contacts.Contact_Name, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID "
        + "FROM appointments, contacts "
        + "WHERE appointments.Contact_ID = contacts.Contact_ID";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Appointments appointments = new Appointments(
                    rs.getInt("Appointment_ID"),
                    rs.getString("Title"),
                    rs.getString("Description"),
                    rs.getString("Location"),
                    rs.getString("Contact_Name"),
                    rs.getString("Type"),
                    startDateTime = rs.getTimestamp("Start").toLocalDateTime(),
                    startDateTime.toLocalDate(),
                    startDateTime.toLocalTime(),
                    endDateTime = rs.getTimestamp("End").toLocalDateTime(),
                    endDateTime.toLocalDate(),
                    endDateTime.toLocalTime(),
                    rs.getInt("Customer_ID"),
                    rs.getInt("User_ID"));
            allAppointments.add(appointments);
        }
        return allAppointments;
    }

    /** Retrieves only appointments for the month
     * @param date
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointments> getAppointmentsForMonth( LocalDate date ) throws SQLException {
        filteredAppointments.clear();
        currentDate = LocalDate.now();
        int currMonth = currentDate.getMonthValue();
        int currYear = currentDate.getYear();
        int searchMonth = date.getMonthValue();
        int searchYear = date.getYear();

        if(currYear != searchYear && currMonth != searchMonth){
            return filteredAppointments;
        } else {
            LocalDateTime startDateTime;
            LocalDateTime endDateTime;

            String sql = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, contacts.Contact_Name, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID "
                    + "FROM appointments, contacts "
                    + "WHERE appointments.Contact_ID = contacts.Contact_ID AND MONTH(Start)=" + searchMonth;
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Appointments appointments = new Appointments(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Contact_Name"),
                        rs.getString("Type"),
                        startDateTime = rs.getTimestamp("Start").toLocalDateTime(),
                        startDateTime.toLocalDate(),
                        startDateTime.toLocalTime(),
                        endDateTime = rs.getTimestamp("End").toLocalDateTime(),
                        endDateTime.toLocalDate(),
                        endDateTime.toLocalTime(),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"));
                filteredAppointments.add(appointments);
            }
            return filteredAppointments;
        }
    }

    /** Retrieves only appointments for the week
     * @param date
     * @return
     * @throws SQLException
     */
    public static ObservableList<Appointments> getAppointmentsForWeek( LocalDate date) throws SQLException {
        filteredAppointments.clear();
        currentDate = LocalDate.now();
        int currentWeek = currentDate.get(WeekFields.ISO.weekOfYear());
        int currentYear = currentDate.getYear();
        int searchWeek = date.get(WeekFields.ISO.weekOfYear());
        int searchYear = date.getYear();

        if(currentYear != searchYear && currentWeek != searchWeek){
            return filteredAppointments;
        } else {
            LocalDateTime startDateTime;
            LocalDateTime endDateTime;

            String sql = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, contacts.Contact_Name, appointments.Type, appointments.Start, appointments.End, appointments.Customer_ID, appointments.User_ID "
                    + "FROM appointments, contacts "
                    + "WHERE appointments.Contact_ID = contacts.Contact_ID AND WEEK(Start)=" + searchWeek;
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Appointments appointments = new Appointments(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Contact_Name"),
                        rs.getString("Type"),
                        startDateTime = rs.getTimestamp("Start").toLocalDateTime(),
                        startDateTime.toLocalDate(),
                        startDateTime.toLocalTime(),
                        endDateTime = rs.getTimestamp("End").toLocalDateTime(),
                        endDateTime.toLocalDate(),
                        endDateTime.toLocalTime(),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"));
                filteredAppointments.add(appointments);
            }
            return filteredAppointments;
        }


    }

    /** Adds appointments to database
     * @param tile
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param customerID
     * @param userID
     * @param contactID
     * @return
     * @throws SQLException
     */
    public static int addAppointment(String tile, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, tile);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setInt(7, customerID);
        ps.setInt(8, userID);
        ps.setInt(9, contactID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
}
