package Model;

/** Users Object
 */
public class Users {

    private int userID;
    private String userName;
    private String password;

    /** Users constructor
     * @param userID
     * @param userName
     * @param password
     */
    public Users(int userID, String userName, String password) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
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

    /**
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** Modifies the default syntax of the toString method for the users class
     * @return
     */
    @Override
    public String toString() {return (userName);}
}
