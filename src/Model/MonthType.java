package Model;

/** Monthtype object
 */
public class MonthType {

    private String year;
    private String month;
    private String type;
    private int count;

    /** Monthtype constructor
     * @param year
     * @param month
     * @param type
     * @param count
     */
    public MonthType(String year, String month, String type, int count) {
        this.year = year;
        this.month = month;
        this.type = type;
        this.count = count;
    }

    /**
     * @return year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year
     */
    public void setYear(String year) {
        this.year = year;
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

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /** Modifies the default syntax of the toString method for the monthtype class
     * @return
     */
    @Override
    public String toString(){
        return(year);
    }
}
