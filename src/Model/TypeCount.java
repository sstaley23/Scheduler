package Model;

/** typecount object
 */
public class TypeCount {

    private String type;
    private int count;

    /** TypeCount constructor
     * @param type
     * @param count
     */
    public TypeCount(String type, int count) {
        this.type = type;
        this.count = count;
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
}
