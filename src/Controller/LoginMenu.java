package Controller;

import DB.UsersDB;
import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/** This class allows users with credentials to navigate to the scheduling app */
public class LoginMenu implements Initializable {

    public Label lblZoneID;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public Label lblError;
    public Label lblScreenName;
    public Label lblUsername;
    public Label lblPassword;
    public Button btnLogin;
    public Label lblAppName1;
    public Label lblAppName2;
    Stage stage;
    Parent scene;

    ResourceBundle rb = ResourceBundle.getBundle("Bundles/Nat_fr", Locale.getDefault());

    /** Method retrieves the username and password. Then calls the checkCredentials method which will either
     * navigate to the main screen or generate an error (in English or French). Also calls the writeToTXT method.
     * @param actionEvent
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    public void onLogin(ActionEvent actionEvent) throws IOException, SQLException {
/* DELETE Commented out so i don't have to login everytime */
//        String username = txtUserName.getText();
//        String password = txtPassword.getText();
//        String result;
//        LocalDate attemptDate = LocalDate.now();
//        LocalTime attemptTime = LocalTime.now();
//        ZoneId userZone = ZoneId.systemDefault();
//
//        if(!checkCredentials(username, password)) {
//            lblError.setText("Invalid login");
//            result = "Failed";
//            if (Locale.getDefault().getLanguage().equals("fr")) {
//                lblError.setText((rb.getString("error")));
//
//            }
//        }else {
//            result = "Successful";
//
//            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
//            scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
//            stage.setTitle("Main Menu");
//            stage.setScene(new Scene(scene));
//            stage.show();
//        }
//
//        writeToTXT(username, attemptDate, attemptTime, userZone, result);

        /*DELETE Below is only for when I want to skip login*/
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setTitle("Main Menu");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Method checks for correct login credentials
     * @param un
     * @param pass
     * @return
     * @throws SQLException
     */
    public boolean checkCredentials(String un, String pass) throws SQLException {
        boolean verdict = false;

        for (Users u : UsersDB.getAllUsers()) {
            if(un.equals(u.getUserName())) {

                String userpswd = u.getPassword();

                if(pass.equals(userpswd)) {
                    verdict = true;
                }
            }
        }
        return verdict;
    }

    /** Method writes the username, date, time, zone id, and whether the login was successful or failed to a txt file for future evaluation
     * @param un
     * @param d
     * @param t
     * @param z
     * @param r
     * @throws IOException
     */
    public void writeToTXT(String un, LocalDate d, LocalTime t, ZoneId z, String r) throws IOException {
        String file = "src/login_activity.txt";
        FileWriter fwriter = new FileWriter(file, true);
        PrintWriter outputFile = new PrintWriter(fwriter);
        outputFile.println(un + " " + d + " " + t + " " + z + " (" + r + ")");
        outputFile.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblZoneID.setText(String.valueOf(ZoneId.systemDefault()));


        if (Locale.getDefault().getLanguage().equals("fr")) {

            lblAppName1.setText(rb.getString("Scheduling"));
            lblAppName2.setText(rb.getString("Application"));
            lblScreenName.setText(rb.getString("Login"));
            lblUsername.setText(rb.getString("Username"));
            lblPassword.setText(rb.getString("Password"));
            btnLogin.setText(rb.getString("Login"));
        }
    }
}
