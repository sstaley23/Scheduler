package Utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;
import java.util.List;

public class TimeManager {

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

    /** Combines a date and time to user's zoneddatetime
     * @param date
     * @param time
     * @return
     */
    public static ZonedDateTime genZDT(LocalDate date, LocalTime time){
        ZoneId userZoneID = ZoneId.systemDefault();
        ZonedDateTime ZDT = ZonedDateTime.of(date, time, userZoneID);

        return ZDT;
    }

    /** Converts a ZDT to UTC LDT
     * @param usersZDT
     * @return
     */
    public static LocalDateTime convertToUTC(ZonedDateTime usersZDT){
        ZoneId utcZoneID = ZoneId.of("UTC");

        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(usersZDT.toInstant(), utcZoneID);
        LocalDateTime utcLDT = utcZDT.toLocalDateTime();

        return utcLDT;
    }
}
