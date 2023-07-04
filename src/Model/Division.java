package Model;

/** Division object
 */
public class Division {

    private int divisionID;
    private String division;
    private int countryID;

    /** Division constructor
     * @param divisionID
     * @param division
     * @param countryID
     */
    public Division(int divisionID, String division, int countryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.countryID = countryID;
    }

    /**
     * @return divisionID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * @param divisionID
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * @return division
     */
    public String getDivision() {
        return division;
    }

    /**
     * @param division
     */
    public void setDivision(String division) {
        this.division = division;
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

    /** Modifies the default syntax of the toString method for the division class
     * @return
     */
    @Override
    public String toString(){
        return(division);
    }
}
