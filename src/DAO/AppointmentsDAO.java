package DAO;

import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public abstract class AppointmentsDAO {

    public static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

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
}
