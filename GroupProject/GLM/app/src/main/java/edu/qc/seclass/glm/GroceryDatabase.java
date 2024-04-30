package edu.qc.seclass.glm;

import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.Set;

import org.json.JSONObject;
import org.json.JSONException;

/**
 * The grocery database stores entries of GroceryItem using a {@link LinkedHashMap } <p>
 * This is a singleton class: only static instance,
 * so the sole dataabase object and its methods can be accessed in any activity
 * @author Jiafeng Lin
 */
public class GroceryDatabase {
    private LinkedHashMap<Integer, GroceryItem> db;
    private static GroceryDatabase instance = null;

    //constructor
    private GroceryDatabase() {
        db = new LinkedHashMap<Integer, GroceryItem>();
    }
    public static GroceryDatabase getInstance() {
        if(instance == null) {
            instance = new GroceryDatabase();
        }
        return instance;
    }

    /**
     * Return a copied instance of item with <b>id</b>
     * @param itemID
     * @return
     */
    public GroceryItem copyItem(int itemID) {
        // Get the item from the database
        GroceryItem original = db.get(itemID);
        if (original != null) {
            // If the item exists, create a new instance of it
            return new GroceryItem(original.getId(), original.getName(), original.getType());
        } else {
            // If the item does not exist, return null or handle the error accordingly
            return null;
        }
    }


    /**
     * Puts a new entry of grocery into the database <p>
     * @param name
     * @param type
     * @return a copy of the new item
     */
    public GroceryItem putItem(int itemID,String name, String type) {
        GroceryItem entry = new GroceryItem(itemID,name, type);
        db.put(entry.getId(), entry);
        //copyItem() is a must to avoid change to the entry in database
        return copyItem(entry.getId());
    }

    /**
     * Search <b>name</b> in database entries and return items of similar name
     * @param name
     * @return an array of grocery items with similar name
     */
    public GroceryItem[] searchItemsByName(String name) {
        //find items of similar name
        TreeMap<Double, GroceryItem> tem = new TreeMap<Double, GroceryItem>();
        Set<Integer> itemIDs = db.keySet();
        for (int id : itemIDs) {
            GroceryItem thisItem = copyItem(id); //again, must copyItem()
            double s = similarity(thisItem.getName(), name);
            if (s > 0.4) //considerably similar
                tem.put(s, thisItem);
        }
        //transfer top 10 from temporary to result array
        GroceryItem[] result = new GroceryItem[10];
        int i = 0;
        GroceryItem item = tem.remove(tem.lastKey()); //get the most similar
        while (i < 10 && item != null) {
            result[i++] = item;
            item = tem.remove(tem.lastKey()); //get the next
        }
        return result;
    }
    /**
     * Returns a double between 0 and 1, 0 means not very similar in length and content
     * and 1 means very similar in length and content <p>
     * @param a
     * @param b
     * @return
     */
    private double similarity(String a, String b) {
        return similarity(a, b, 3); //default to 3 characters
    }
    /**
     * Returns a double between 0 and 1, 0 means not very similar in length and content
     * and 1 means very similar in length and content <p>
     * Overlap determines how much to favor consecutive blocks
     * @param a
     * @param b
     * @param overlap
     * @return
     */
    private double similarity(String a, String b, int overlap) {
        //find longer and shorter
        String longer = a, shorter = b;
        if (a.length() < b.length()) {
            shorter = a;
            longer = b;
        }
        if (longer.length() == 0)
            return 1.0;
        shorter = shorter.toLowerCase();
        longer = longer.toLowerCase();
        //calculate rough perfect score (i.e. all consecutive match)
        int perfect = 0;
        {
            int l = shorter.length();
            for (int i = 1; i <= shorter.length() && i < overlap; i++) {
                perfect += i;
                l--;
            }
            perfect += l*overlap;
        }
        //find actual score
        int score = 0, consec = 0, graced = 0, start;
        for (int l = 0; l < longer.length(); l++) {
            start = l;
            for (int s = 0; s < shorter.length() && start+consec < longer.length(); s++) {
                if (longer.charAt(start+consec) == shorter.charAt(s)) { //is a hit,
                    if (consec < overlap) //increase next consecutive score
                        consec++;
                    else
                        start++;
                    if (consec > overlap/2) //probably not a coincidence
                        l = start+consec; //move l along
                    score += consec;
                    //reduce graced
                    if (graced > 0)
                        graced--;
                }
                else if (consec > 0) { //failed, reset!
                    start = l;
                    consec = 0;
                }
            }
            if (graced < overlap/2)
                graced++;
            else
                score--;
        }
        double p = score/(double)perfect;
        if (p > 1.0)
            p = 1.0;
        else if (p < 0.0)
            p = 0.0;
        return p;
    }

    /**
     * <b>Copies</b> all entries of <b>type</b> in the database to a list
     * @return the new list of type
     */
    public ListOfType getListOfType(String type) {
        ListOfType typeList = new ListOfType(type);
        Set<Integer> itemIDs = db.keySet();
        for (int id : itemIDs)
            //copy a new instance of the item to list
            typeList.addItem(copyItem(id));
        return typeList;
    }

    /**
     * Returns a JSONObject containing all grocery entries such that <p>
     * "itemID" : itemObject <p>
     * "itemID" : itemObject <p>
     * "itemID" : itemObject
     * @return
     */
    public JSONObject getJSONObject() throws JSONException {
        JSONObject dbJson = new JSONObject();
        Set<Integer> itemIDs = db.keySet();
        for (int id : itemIDs)
            dbJson.put(""+id, db.get(id).getJSONObject());
        return dbJson;
    }


}
