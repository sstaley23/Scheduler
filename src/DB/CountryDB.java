package DB;

import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Class provides various methods and data retrieval for country related information
 */
public abstract class CountryDB {

    static ObservableList<Country> allCountries = FXCollections.observableArrayList();

    /** Method retrieves country data from database
     * @return
     * @throws SQLException
     */
    public static ObservableList<Country> getAllCountries() throws SQLException {
        allCountries.clear();

        String sql = "SELECT Country_ID, Country FROM countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Country countries = new Country(
                    rs.getInt("Country_ID"),
                    rs.getString("Country"));
            allCountries.add(countries);
        }
        return allCountries;
    }
}
