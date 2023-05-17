package Utilities;

import Controller.CustomerAdd;
import Model.Country;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CountryAndDivisionQuery {

    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();
    private static ObservableList<Division> allDivisions = FXCollections.observableArrayList();

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

    public static  ObservableList<Division> getDivisions() throws SQLException {
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
