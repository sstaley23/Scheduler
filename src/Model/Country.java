package Model;

/** Country Object
 */
public class Country {

    private int countryID;
    private String country;

    /** country constructor
     * @param countryID
     * @param country
     */
    public Country(int countryID, String country) {
        this.countryID = countryID;
        this.country = country;
    }

    /**
     * @return countryID
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * @param countryID
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /** Modifies the default syntax of the toString method for the class
     * @return
     */
    @Override
    public String toString(){
        return(country);
    }
}
