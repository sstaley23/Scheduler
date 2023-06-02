package Utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class TimeManager {

    public static List<ZonedDateTime> genUTCBusinessHours() {
        ObservableList<ZonedDateTime> UTCBusinessHours = FXCollections.observableArrayList();
        LocalDate UTCBusinessStartDate = LocalDate.now();
        LocalTime UTCBusinessStartTime = LocalTime.NOON;
        ZoneId UTCZone = ZoneId.of("UTC");
        ZonedDateTime UTCBusinessStart = ZonedDateTime.of(UTCBusinessStartDate, UTCBusinessStartTime, UTCZone);
        ZonedDateTime UTCBusinessEnd = UTCBusinessStart.plusHours(14);

        while(UTCBusinessStart.isBefore(UTCBusinessEnd.plusSeconds(1))){
            UTCBusinessHours.add(UTCBusinessStart);
            //System.out.println(UTCBusinessStart.toLocalTime() + "[" + UTCZone + "]");
            UTCBusinessStart = UTCBusinessStart.plusMinutes(15);
        }
        return UTCBusinessHours;
    }

    public static ObservableList<ZonedDateTime> genUserBusinessHours() {
        ObservableList<ZonedDateTime> UTCBusinessHours = (ObservableList<ZonedDateTime>) genUTCBusinessHours();
        ObservableList<ZonedDateTime> UserBusinessHours = FXCollections.observableArrayList();
        ZoneId UserZone = ZoneId.systemDefault();

        for(ZonedDateTime utc : UTCBusinessHours){
            UserBusinessHours.add(ZonedDateTime.ofInstant(utc.toInstant(), UserZone));
        }

//        for(ZonedDateTime user : UserBusinessHours){
//            System.out.println(user.toLocalTime() + "[" + UserZone + "]");
//        }
        return UserBusinessHours;
    }



}
