package edu.qc.seclass.glm;

import android.content.Context;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * The ListOfType represents an shelf of grocery items of the same type, it
 * uses a {@link LinkedHashMap } to store its set of grocery items
 * @author Jiafeng Lin
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Set;

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
     * Adds an item to this ListOfType
     *
     * @param item
     */
    public void addItem(GroceryItem item) {
        items.put(item.getId(), item);
    }

    /**
     * Selects the grocery item of matching ID
     *
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
     *
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
     *
     * @param item
     * @param targetList
     */
    public void addItemToList(GroceryItem item, GroceryList targetList) {
        targetList.addItem(item);
    }

    /**
     * Loads types from the item_database.json file
     *
     * @param context The context to access file resources
     * @return A set of types loaded from the file
     */
    public static Set<String> loadTypesFromJSON(Context context) {
        Set<String> typesSet = new HashSet<>();
        try {
            // Read JSON data from file
            FileInputStream fis = context.openFileInput("item_database.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder jsonData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
            reader.close();

            // Parse JSON data and extract types
            JSONObject dbJson = new JSONObject(jsonData.toString());
            for (Iterator<String> it = dbJson.keys(); it.hasNext(); ) {
                String key = it.next();
                JSONObject itemJson = dbJson.getJSONObject(key);
                typesSet.add(itemJson.getString("type"));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return typesSet;
    }
}
