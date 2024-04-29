package edu.qc.seclass.glm;

import java.util.LinkedHashMap;
import java.util.Set;

import org.json.JSONObject;
import org.json.JSONException;

/**
 * The grocery database stores entries of GroceryItem using a {@link LinkedHashMap }
 * @author Jiafeng Lin
 */
public class GroceryDatabase {
    private static LinkedHashMap<Integer, GroceryItem> db; //should be static, well,
        //there is only going to be one database anyway
    
    //constrcutor
    public GroceryDatabase() {
        db = new LinkedHashMap<Integer, GroceryItem>();
    }

    /**
     * Search <b>name</b> in database entries and return items of similar name
     * @param name
     * @return an array of grocery items with similar name
     */
    public GroceryItem[] searchItemsByName(String name) {
        return null; //placeholder
    }

    /**
     * <b>Copies</b> all entries of <b>type</b> in the database to a list
     * @return the new list of type
     */
    public ListOfType getListOfType(String type) {
        ListOfType typeList = new ListOfType();
        Set<Integer> itemIDs = db.keySet();
        for (int id : itemIDs) {
            //get the item
            GroceryItem original = db.get(id);
            //copy a new instance of the item to list
            typeList.addItem(new GroceryItem(original.getId(), original.getName(), original.getType()));
        }
        return typeList;
    }

    /**
     * Returns a JSONObject containing all grocery entries such that <p>
     * "itemID" : itemObject <p>
     * "itemID" : itemObject <p>
     * "itemID" : itemObject
     * @return
     */
    public getJSONObject() {
        JSONObject dbJson = new JSONObject();
        Set<Integer> itemIDs = db.keySet();
        for (int id : itemIDs)
            dbJson.put(""+id, db.get(id).getJSONObject());
        return listJson;
    }
}
