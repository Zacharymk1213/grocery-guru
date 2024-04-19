package edu.qc.seclass.glm;

/**
 * a node class representing a grocery item
 * @author Jiafeng Lin
 */
public class GroceryItem {
    private static int idCount;
    private int id;
    private String name;
    private String type;
    private int quanity = 0;
    public boolean isSeleted = false;

    //constructor
    //use this only for adding new entry into database
    public GroceryItem(String n, String t) {
        id = idCount++;
        name = n;
        type = t;
    }
    //for cloning an old item from database; hence id is known
    public GroceryItem(int d, String n, String t) {
        id = d;
        name = n;
        type = t;
    }

    //access methods
    public int getId() { return id; }
    //setId() is an invalid operation

    public String getName() { return name; }
    public void setName(String n) { name = n; }

    public String getType() { return type; }
    public void setType(String t) { type = t; }

    public boolean isSeleted() { return isSeleted; }
    public void setSeleted(boolean tf) { isSeleted = tf; }

    /**
     * Set postive <b>amount</b> to increase, negative <b>amount</b> to decrease
     * @param amount to be changed
     */
    public void updateQuanity(int amount) {
        quanity += amount;
    }

    public String toString() {
        return "{" + id + ", " + name + ", " + quanity + "}";
    }
}
