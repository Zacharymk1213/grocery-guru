package edu.qc.seclass.glm;

/**
 * a node class representing a grocery item
 * @author Jiafeng Lin
 */
public class GroceryItem {
    private int id;
    private String name;
    private String type;
    private int quanity = 0;
    public boolean isSeleted = false;

    //constructor
    public GroceryItem(String n, String t) {
        name = n;
        type = t;
    }

    //access methods
    public int getId() { return id; }
    public void setId(int d) { id = d; }

    public String getName() { return name; }
    public void setName(String n) { name = n; }

    public String getType() { return type; }
    public void setType(String t) { type = t; }

    public boolean isSeleted() { return isSeleted; }
    public void setSeleted(boolean tf) { isSeleted = tf; }

    /**
     * postive <b>amount</b> to increase, negative <b>amount</b> to decrease
     * @param amount to be changed
     */
    public void updateQuanity(int amount) {
        quanity += amount;
    }

    public String toString() {
        return "{" + id + ", " + name + ", " + quanity + "}";
    }
}
