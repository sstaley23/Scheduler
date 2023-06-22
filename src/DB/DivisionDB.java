package DB;

import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DivisionDB {

    public static ObservableList<Division> allDivisions = FXCollections.observableArrayList();

    public static  ObservableList<Division> getAllDivisions() throws SQLException {
        allDivisions.clear();

        String sql = "SELECT Division_ID, Division, Country_ID FROM first_level_divisions";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Division divisions = new Division(
                    rs.getInt("Division_ID"),
                    rs.getString("Division"),
                    rs.getInt("Country_ID"));
            allDivisions.add(divisions);
        }
        return allDivisions;
    }
}
