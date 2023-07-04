package Utilities;

import DB.AppointmentsDB;
import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.*;
import java.util.List;

/** Class provides various methods for dealing with time and time conversions
 */
public class TimeManager {
    /**Variable captures the localDateTime at a successful login
     */
    public static LocalDateTime glLogin;

    /** Generates the business hours in UTC
     * @return
     */
    public static List<ZonedDateTime> genUTCBusinessHours() {
        ObservableList<ZonedDateTime> UTCBusinessHours = FXCollections.observableArrayList();
        LocalDate UTCBusinessStartDate = LocalDate.now();
        LocalTime UTCBusinessStartTime = LocalTime.NOON;
        ZoneId UTCZone = ZoneId.of("UTC");
        ZonedDateTime UTCBusinessStart = ZonedDateTime.of(UTCBusinessStartDate, UTCBusinessStartTime, UTCZone);
        ZonedDateTime UTCBusinessEnd = UTCBusinessStart.plusHours(14);

        while(UTCBusinessStart.isBefore(UTCBusinessEnd.plusSeconds(1))){
            UTCBusinessHours.add(UTCBusinessStart);
            UTCBusinessStart = UTCBusinessStart.plusMinutes(15);
        }
        return UTCBusinessHours;
    }

    /** Takes the list from genUTCBusinessHours() method, converts to user ZDT, and returns a list business hours in user's time zone
     * @return
     */
    public static ObservableList<LocalTime> genUserBusinessHours() {
        ObservableList<ZonedDateTime> UTCBusinessHours = (ObservableList<ZonedDateTime>) genUTCBusinessHours();
        ObservableList<ZonedDateTime> UserZDT = FXCollections.observableArrayList();
        ObservableList<LocalTime> UserBusinessHours = FXCollections.observableArrayList();
        ZoneId UserZone = ZoneId.systemDefault();

        for(ZonedDateTime utc : UTCBusinessHours){
            UserZDT.add(ZonedDateTime.ofInstant(utc.toInstant(), UserZone));
        }

        for(ZonedDateTime user : UserZDT) {
            UserBusinessHours.add(user.toLocalTime());
        }
        return UserBusinessHours;
    }

    /** Checks for appointment overlaps and returns true if found
     * @param start
     * @param end
     * @param id
     * @return
     * @throws SQLException
     */
    public static boolean checkOverlap(LocalDateTime start, LocalDateTime end, int id) throws SQLException {
        boolean overlap = false;
        ObservableList<Appointments> appts = AppointmentsDB.getCustomerAppointments(id);

        for(Appointments appt : appts){
            //Scenario 1
            if((start.isAfter(appt.getStartDateTime()) || start.isEqual(appt.getStartDateTime())) && start.isBefore(appt.getEndDateTime())) {
                System.out.println("Scenario 1 triggered");
                overlap = true;
            }
            //Scenario 2
            if((end.isBefore(appt.getEndDateTime()) || end.isEqual(appt.getEndDateTime())) && end.isAfter(appt.getStartDateTime())){
                System.out.println("Scenario 2 triggered");
                overlap = true;
            }
            //Scenario 3
            if((start.isBefore(appt.getStartDateTime()) || start.isEqual(appt.getStartDateTime())) && (end.isAfter(appt.getEndDateTime()) || end.isEqual(appt.getEndDateTime()))) {
                System.out.println("Scenario 3 triggered");
                overlap = true;
            }
        }
        return overlap;
    }

}
