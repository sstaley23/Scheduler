package Model;

/** Class creates customer objects */
public class Customers {

    private int id;
    private String name;
    private String address;
    private String firstLevel;
    private String country;
    private String postalCode;
    private String phoneNumber;


    /** customer constructor
     * @param id
     * @param name
     * @param address
     * @param firstLevel
     * @param country
     * @param postalCode
     * @param phoneNumber
     */
    public Customers(int id, String name, String address, String firstLevel, String country, String postalCode, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.firstLevel = firstLevel;
        this.country = country;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return firstlevel
     */
    public String getFirstLevel() {
        return firstLevel;
    }

    /**
     * @param firstLevel
     */
    public void setFirstLevel(String firstLevel) {
        this.firstLevel = firstLevel;
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

    /**
     * @return postalcode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /** Modifies the default syntax of the toString method for the customer class
     * @return
     */
    @Override
    public String toString(){
        return (name);
    }
}

