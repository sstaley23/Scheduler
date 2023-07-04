package Model;

/** Contacts object
 */
public class Contacts {
    private int contactID;
    private String contactName;
    private String contactEmail;

    /** contacts constructor
     * @param contactID
     * @param contactName
     * @param contactEmail
     */
    public Contacts(int contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /**
     * @return contactID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * @param contactID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * @return contactName
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
     * @return contactEmail
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * @param contactEmail
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /** Modifies the default syntax of the toString method for the contacts class
     * @return
     */
    @Override
    public String toString(){
        return (contactName);
    }
}
