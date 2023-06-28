package DB;

import Model.repMonthType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ReportsDB {


    /** Generates a list of RepMonthTypes
     * @return
     * @throws SQLException
     */
    public static ObservableList<repMonthType> getMonthTypeAppts() throws SQLException {

        ObservableList<repMonthType> monthTypeAppts = FXCollections.observableArrayList();

        String sql = "SELECT Year(Start) as Year, monthname(Start) as Month, Type, count(*) as Count "  +
                "FROM appointments " +
                "GROUP BY Year, Month, type";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            repMonthType query = new repMonthType(
                    rs.getString("Year"),
                    rs.getString("Month"),
                    rs.getString("Type"),
                    rs.getInt("Count"));
            monthTypeAppts.add(query);
        }
        return monthTypeAppts;
    }
}
