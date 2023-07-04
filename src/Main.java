import DB.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;

/** Main class that handles start upt and database connection
 */
public class Main extends Application {

    /** Loads loginmenu at start up
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/LoginMenu.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    /** Open connection with database, launch args, and closes connection with database on exit
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
