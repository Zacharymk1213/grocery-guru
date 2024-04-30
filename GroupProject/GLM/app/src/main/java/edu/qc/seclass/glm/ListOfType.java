package edu.qc.seclass.glm;

import java.util.LinkedHashMap;
import java.util.Set;

/**
 * The ListOfType represents an shelf of grocery items of the same type, it
 * uses a {@link LinkedHashMap } to store its set of grocery items
 * @author Jiafeng Lin
 */
public class ListOfType {
    private String type;
    private LinkedHashMap<Integer, GroceryItem> items;

    //constructor
    public ListOfType(String t) {
        type = t;
        items = new LinkedHashMap<>();
    }

    //access methods
    public String getType() { return type; }

    /**
     * Adds an item to the this ListOfType
     * @param item
     */
    public void addItem(GroceryItem item) {
        items.put(item.getId(), item);
    }

    /**
     * Select the grocery item of matching ID
     * @param itemID
     */
    public void selectItem(int itemID) {
        GroceryItem item = items.get(itemID);
        if (item != null) {
            item.setSelected(true);
        }
    }

    /**
     * Adds all selected items in this ListOfType to targetList
     * @param targetList
     */
    public void addSelectedToList(GroceryList targetList) {
        for (GroceryItem item : items.values()) {
            if (item.isSelected()) {
                targetList.addItem(item);
            }
        }
    }

    /**
     * Adds the clicked item to targetList
     * @param item
     * @param targetList
     */
    public void addItemToList(GroceryItem item, GroceryList targetList) {
        targetList.addItem(item);
    }
}

