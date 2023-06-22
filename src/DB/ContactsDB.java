package DB;

import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ContactsDB {

    public static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

    /** Retrieves a list of contacts from database
     * @return
     * @throws SQLException
     */
    public static ObservableList<Contacts> getAllContacts() throws SQLException {
        allContacts.clear();

        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Contacts contacts = new Contacts(
                    rs.getInt("Contact_ID"),
                    rs.getString("Contact_Name"),
                    rs.getString("Email"));
            allContacts.add(contacts);
        }
        return  allContacts;
    }

    //Delete this if not utilized
    public static String getContactByID(int i) throws SQLException {
        String foundContact = null;

        for (Contacts c : ContactsDB.getAllContacts()) {
            if (i == c.getContactID()){
                foundContact = c.getContactName();
            }
        }
        return foundContact;
    }

    /** Finds contact's id when given a name
     * @param name
     * @return
     * @throws SQLException
     */
    public static int findContactID(String name) throws SQLException {
        int id = -1;
        ObservableList<Contacts> contacts = getAllContacts();

        for(Contacts contact : contacts){
            if(name.equals(contact.getContactName())){
                id = contact.getContactID();
                break;
            }
        }
        return id;
    }
}
