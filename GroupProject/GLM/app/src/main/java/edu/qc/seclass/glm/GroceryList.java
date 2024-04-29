package edu.qc.seclass.glm;

import java.util.LinkedHashMap;
import java.util.Set;

import org.json.JSONObject;
import org.json.JSONException;

/**
 * The grocery list represents a user's list of to-buy groceries, it
 * uses a {@link LinkedHashMap } to store its set of grocery items
 * @author Jiafeng Lin
 */
public class GroceryList {
    private static int idCount;
    private int id;
    private String name;
    private LinkedHashMap<Integer, GroceryItem> list; //should not be static
    private boolean isSeleted = false;

    //constructor
    public GroceryList(String n) {
        id = idCount++;
        name = n;
        list = new LinkedHashMap<>();
        // Assign the context
    }
    //for cloning an old list from userdata; hence id is known
    public GroceryList(int d, String n) {
        if (d >= idCount)
            idCount = d+1;
        id = d;
        name = n;
        list = new LinkedHashMap<>();
    }

    //access methods
    public int getId() { return id; }
    //setId() is an invalid operation

    public String getName() { return name; }
    public void setName(String n) { name = n; }

    public boolean isSeleted() { return isSeleted; }
    public void setSeleted(boolean tf) { isSeleted = tf; }

    public LinkedHashMap<Integer, GroceryItem> getList() { return list; }

    /**
     * Return the item from grocery list if it exists
     * @param id
     * @return the item of matching id
     */
    public GroceryItem searchItem(int id) {
        return list.get(id);
    }

    /**
     * Adds an item to the grocery list
     * @param item
     */
    public void addItem(GroceryItem item) {
        list.put(item.getId(), item);
    }
    
    /**
     * Removes item of ID from grocery list and returns it
     * @param itemID
     * @return the <b>item</b> deleted
     */
    public GroceryItem deleteItem(int itemID) {
        return list.remove(itemID);
    }
    
    /**
     * Change the quantity of item of ID;
     * postive <b>amount</b> to increase, negative <b>amount</b> to decrease
     * @param itemID
     * @param amount
     */
    public void changeQuantity(int itemID, int amount) {
        searchItem(itemID).updateQuantity(amount);
    }

    /**
     * Mark the grocery item on list as checkedOff
     * @param itemID
     */
    public void checkOff(int itemID) {
        GroceryItem item = searchItem(itemID);
        if (item != null) {
            item.setSeleted(true);
        }
        // No toast message shown when the item is not found in the list
    }

    /**
     * Iterates through the entire list, clear off their selection
     */
    public void clearCheckOff() {
        Set<Integer> itemIDs = list.keySet();
        for (int id : itemIDs)
            list.get(id).setSeleted(false);
    }

    /**
     * Returns a JSONObject containing list data such that <p>
     * "id" : id <p>
     * "name" : name <p>
     * "isSeleted" : false/true <p>
     * "list" : { <p>
     *     "itemID" : itemObject <p>
     *     "itemID" : itemObject <p>
     * }
     * @return
     */
    public JSONObject getJSONObject() throws JSONException {
        JSONObject listJson = new JSONObject();
        listJson.put("id", id);
        listJson.put("name", name);
        listJson.put("isSeleted", isSeleted);
        JSONObject itemsJson = new JSONObject();
        //put listObjects
        Set<Integer> itemIDs = list.keySet();
        for (int id : itemIDs)
            itemsJson.put(""+id, list.get(id).getJSONObject());
        listJson.put("list", itemsJson);
        return listJson;
    }
}
