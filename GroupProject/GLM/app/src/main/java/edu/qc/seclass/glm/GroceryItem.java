package edu.qc.seclass.glm;

import org.json.JSONObject;
import org.json.JSONException;

/**
 * GroceryItem represents a grocery item
 * @author Jiafeng Lin
 */
public class GroceryItem {
    private static int idCount;
    private int id;
    private String name;
    private String type;
    private int quantity = 0;
    public boolean isSelected = false;

    //constructor
    //use this only for adding new entry into database
    public GroceryItem(String n, String t) {
        id = idCount++;
        name = n;
        type = t;
    }
    //for cloning an old item from database; hence id is known
    public GroceryItem(int d, String n, String t) {
        if (d >= idCount)
            idCount = d+1;
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

    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean tf) { isSelected = tf; }

    /**
     * Set postive <b>amount</b> to increase, negative <b>amount</b> to decrease
     * @param amount to be changed
     */
    public void updateQuantity(int amount) {
        if (this != null) {
            quantity += amount;
        } else {
            // Handle the case where the GroceryItem object is null
            // For example, you can log an error message
            System.err.println("Error: Attempted to update quantity on a null GroceryItem object");
        }
    }


    public String toString() {
        return "{" + id + ", " + name + ", " + quantity + "}";
    }

    /**
     * Returns a JSONObject containing item data such that <p>
     * "id" : id <p>
     * "name" : name <p>
     * "type" : type <p>
     * "quantity" : quantity <p>
     * "isSelected" : false/true
     * @return
     */
    public JSONObject getJSONObject() throws JSONException {
        JSONObject itemJson = new JSONObject();
        itemJson.put("id", id);
        itemJson.put("name", name);
        itemJson.put("type", type);
        itemJson.put("quantity", quantity);
        itemJson.put("isSelected", isSelected);
        return itemJson;
    }
}
