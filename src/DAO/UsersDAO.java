package DAO;

import Model.Contacts;
import Model.Customers;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UsersDAO {

    public static ObservableList<Users> allUsers = FXCollections.observableArrayList();

    /** Retrieves a list of users from database
     * @return
     * @throws SQLException
     */
    public static ObservableList<Users> getAllUsers() throws SQLException {
        allUsers.clear();

        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Users users = new Users(
                    rs.getInt("User_ID"),
                    rs.getString("User_Name"),
                    rs.getString("Password"));
            allUsers.add(users);
        }
        return allUsers;
    }

    /** Finds user's id when given a name
     * @param name
     * @return
     * @throws SQLException
     */
    public static int findUserID(String name) throws SQLException {
        int id = -1;
        ObservableList<Users> users = getAllUsers();

        for(Users user : users){
            if(name.equals(user.getUserName())){
                id = user.getUserID();
                break;
            }
        }
        return id;
    }
}
