package DAO;

import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ContactsDAO {

    public static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

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

    public static String getContactByID(int i) throws SQLException {
        String foundContact = null;

        for (Contacts c : ContactsDAO.getAllContacts()) {
            if (i == c.getContactID()){
                foundContact = c.getContactName();
            }
        }
        return foundContact;
    }
}
