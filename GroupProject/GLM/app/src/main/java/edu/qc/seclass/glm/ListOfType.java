package edu.qc.seclass.glm;

import java.util.LinkedHashMap;

/**
 * The ListOfType represents an shelf of grocery items of the same type, it
 * uses a {@link LinkedHashMap } to store its set of grocery items
 * @author Jiafeng Lin
 */
public class ListOfType {
    //id ommited since ListOfType is only temporary
    //and only one will be opened at a time
    private String type;
    private LinkedHashMap<Integer, GroceryItem> items;

    //constructor
    public ListOfType(String t) {
        type = t;
        items = new LinkedHashMap<Integer, GroceryItem>();
    }

    //access methods
    public String getType() { return type; }
    //setType() is illegal in this context

    /**
     * Adds an item to the this ListOfType
     * @param item
     */
    public void addItem(GroceryItem item) {
        items.put(item.getID(), item);
    }

    /**
     * Select the grocery item of matching ID
     * @param itemID
     */
    public void selectItem(int itemID) {
        searchItem(itemID).setSeleted(true);
    }

    /**
     * Adds all selected items in this ListOfType to <b>targetList</b>
     * @param item
     */
    public void addSelectedToList(GroceryList targetList) {
        Set<Integer> itemIDs = items.keySet();
        for (int id : itemIDs) {
            GroceryItem item = items.get(id);
            if (item.isSeleted())
                targetList.addItem(item);
        }
    }

    /**
     * Adds the <i>clicked</i> item to <b>targetList</b>
     * @param item
     */
    public void addItemToList(GroceryItem item, GroceryList targetList) {
        targetList.addItem(item);
    }
}
