package Model;

public class repMonthType {

    private String year;
    private String month;
    private String type;
    private int count;

    public repMonthType(String year, String month, String type, int count) {
        this.year = year;
        this.month = month;
        this.type = type;
        this.count = count;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString(){
        return(year);
    }
}
