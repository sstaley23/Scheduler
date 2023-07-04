package Model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/** Appointment object
 */
public class Appointments {

    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String contactName;
    private String type;
    private LocalDateTime startDateTime;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDateTime endDateTime;
    private LocalDate endDate;
    private LocalTime endTime;
    private int customerID;
    private int userID;

    /** Appointment constructor
     * @param appointmentID
     * @param title
     * @param description
     * @param location
     * @param contactName
     * @param type
     * @param startDateTime
     * @param startDate
     * @param startTime
     * @param endDateTime
     * @param endDate
     * @param endTime
     * @param customerID
     * @param userID
     */
    public Appointments(int appointmentID, String title, String description, String location, String contactName, String type, LocalDateTime startDateTime, LocalDate startDate, LocalTime startTime, LocalDateTime endDateTime, LocalDate endDate, LocalTime endTime, int customerID, int userID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contactName = contactName;
        this.type = type;
        this.startDateTime = startDateTime;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDateTime = endDateTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.customerID = customerID;
        this.userID = userID;
    }

    /**
     * @return apptId
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * @param appointmentID
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return contact
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return startdatetime
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * @param startDateTime
     */
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * @return startdate
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * @return starttime
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @return enddatetime
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * @param endDateTime
     */
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     * @return enddate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * @return endtime
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * @return customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
}
