package Model;

/** Month object
 */
public class Month {
    String month;

    /** Month constructor
     * @param month
     */
    public Month(String month) {
        this.month = month;
    }

    /**
     * @return month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /** Modifies the default syntax of the toString method for the month class
     * @return
     */
    @Override
    public String toString() {return(month);}
}
