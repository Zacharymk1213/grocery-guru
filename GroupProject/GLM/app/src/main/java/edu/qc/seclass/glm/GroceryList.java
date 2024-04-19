package edu.qc.seclass.glm;

import java.util.LinkedHashMap;

/**
 * The grocery list uses a {@link LinkedHashMap } to store its set of grocery items
 * @author Jiafeng Lin
 */
public class GroceryList {
    private String name;
    private LinkedHashMap<Integer, GroceryItem> list;
    private boolean isSeleted = false;

    //constructor
    public GroceryList(String n) {
        name = n;
        list = new LinkedHashMap<Integer, GroceryItem>();
    }

    //access methods
    public String getName() { return name; }
    public void setName(String n) { name = n; }

    public boolean isSeleted() { return isSeleted; }
    //this will work as checkOff(): simply setSeleted(true)
    public void setSeleted(boolean tf) { isSeleted = tf; }

    public LinkedHashMap<Integer, GroceryItem> getList() { return list; }

    /**
     * get the item from grocery list if it exists
     * @param id
     * @return the item of matching id
     */
    public GroceryItem searchItem(int id) {
        return list.get(id);
    }

    /**
     * adds an item to the grocery list
     * @param item
     */
    public void addItem(GroceryItem item) {
        list.put(item.getID(), item);
    }
    
    /**
     * removes item of ID from grocery list
     * @param itemID
     * @return the <b>item</b> deleted
     */
    public GroceryItem deleteItem(int itemID) {
        return list.remove(itemID);
    }
    
    /**
     * change the quanity of item of ID;
     * postive <b>amount</b> to increase, negative <b>amount</b> to decrease
     * @param itemID
     * @param amount
     */
    public void changeQuanity(int itemID, int amount) {
        searchItem(itemID).updateQuanity(amount);
    }

    /**
     * Iterates through the entire list, clear off their selection
     */
    public void clearCheckOff() {
        Set<Integer> itemIDs = list.keySet();
        for (int id : itemIDs)
            list.get(id).setSeleted(false);
    }
}
