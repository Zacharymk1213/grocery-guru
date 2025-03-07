package edu.qc.seclass.glm;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Iterator;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;
import org.json.JSONException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * The grocery database stores entries of GroceryItem using a {@link LinkedHashMap } <p>
 * This is a singleton class: only static instance,
 * so the sole dataabase object and its methods can be accessed in any activity
 * @author Jiafeng Lin
 */
public class GroceryDatabase {
    private Context appContext;
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
     * sets the context for save and load
     * @param c
     */
    public void setContext(Context c) {
        appContext = c;
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
     * Will only put a existing item into database without returning the object <p>
     * this should only be used to load json data into the database
     * @param id
     * @param name
     * @param type
     */
    public void putItem(int id, String name, String type) {
        GroceryItem entry = new GroceryItem(id, name, type);
        db.put(id, entry);
    }
    /**
     * Puts a new entry of grocery into the database <p>
     * this should only be used in NewEntryActivity to add new database entry
     * @param name
     * @param type
     * @return a copy of the new item
     */
    public GroceryItem putItem(String name, String type) {
        GroceryItem entry = new GroceryItem(name, type);
        db.put(entry.getId(), entry);
        //copyItem() is a must to avoid change to the entry in database
        return copyItem(entry.getId());
    }

    public void remove(int itemId) {
        db.remove(itemId);
    }

    /**
     * @return all the item entries in database
     */
    public ArrayList<GroceryItem> getAllItems() {
        return new ArrayList<GroceryItem>(db.values());
    }

    /**
     * Search <b>name</b> in database entries and return items of similar name
     * @param name
     * @return an array of grocery items with similar name
     */
    public ArrayList<GroceryItem> searchItemsByName(String name) {
        //find items of similar name
        TreeMap<Double, GroceryItem> tem = new TreeMap<Double, GroceryItem>();
        Set<Integer> itemIDs = db.keySet();
        for (int id : itemIDs) {
            GroceryItem thisItem = copyItem(id); //again, must copyItem()
            double s = similarity(thisItem.getName(), name);
            //put into tree with similarity as key to sort
            //reduce s slightly until it is not a duplicate key
            if (s > 0) {
                while (tem.containsKey(s))
                    s -= 0.0000001; //I think sufficient enough
                tem.put(s, thisItem);
            }
        }
        //transfer top 10 from temporary to result array
        int resultSize = tem.size();
        if (resultSize > 10)
            resultSize = 10;
        ArrayList<GroceryItem> result = new ArrayList<GroceryItem>();
        int i = 0;
        while (i < resultSize)
            result.add(i++, tem.remove(tem.lastKey())); //get the most similar
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
        int score = 0, consec = 0, start;
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
                }
                else if (consec > 0) { //failed, reset!
                    start = l;
                    consec = 0;
                }
            }
        }
        double p = score/(double)perfect;
        if (p > 1.0)
            p = 1.0;
        else if (p < 0.0)
            p = 0.0;
        return p;
    }

    /**
     * @return set of types that exists in the database
     */
    public Set<String> getTypes() {
        Set<String> typesSet = new HashSet<String>();
        Set<Integer> itemIDs = db.keySet();
        for (int id : itemIDs)
            typesSet.add(db.get(id).getType());
        return typesSet;
    }

    /**
     * <b>Copies</b> all entries of <b>type</b> in the database to a list
     * @return the new list of type
     */
    public ListOfType getListOfType(String type) {
        ListOfType typeList = new ListOfType(type);
        Set<Integer> itemIDs = db.keySet();
        for (int id : itemIDs)
            //copy a new instance of the item to list if type matches
            if (db.get(id).getType().equals(type))
                typeList.addItem(copyItem(id));
        return typeList;
    }

    /**
     * Returns true if database already contains an entry <p>
     * with same name and type, regardless of case
     * @param name
     * @param type
     * @return boolean
     */
    public boolean hasNameType(String name, String type) {
        name = name.toLowerCase();
        type = type.toLowerCase();
        Set<Integer> itemIDs = db.keySet();
        for (int id : itemIDs) {
            GroceryItem thisItem = db.get(id);
            if (thisItem.getName().toLowerCase().equals(name)
                && thisItem.getType().toLowerCase().equals(type))
                return true;
        }
        return false;
    }

    /**
     * @return true if the database has no entry
     */
    public boolean isEmpty() {
        return db.size() == 0;
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
    
    /**
     * save all grocery item entries in database to item_database.json
     * @return 0 if save is successful
     */
    public int saveDatabase() {
        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(appContext.openFileOutput("item_database.json", 0))
        )) {
            // Create JSON object for user data
            JSONObject userDataJson = getJSONObject();
            // Write JSON data to file
            out.write(userDataJson.toString());
            out.close();
            return 0; // Success
        } catch (IOException e) {
            // Handle IO exception
            Log.e("SaveUserData", "Error writing JSON file: " + e.getMessage());
        } catch (JSONException e) {
            // Handle JSON exception
            Log.e("SaveUserData", "Error creating JSON: " + e.getMessage());
        }
        return -1; // Error
    }

    /**
     * load all grocery item entries in database from item_database.json
     * @return 0 if load is successful
     */
    public int loadDatabase() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(appContext.openFileInput("item_database.json"))
        )) {
            StringBuilder jsonData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }

            // Parse JSON data
            JSONObject dbJson = new JSONObject(jsonData.toString());
            // Load database
            //GroceryDatabase database = GroceryDatabase.getInstance();
            Iterator<String> entries = dbJson.keys();
            while (entries.hasNext()) {
                String key = entries.next();
                JSONObject itemJson = dbJson.getJSONObject(key);
                int id = itemJson.getInt("id");
                String name = itemJson.getString("name");
                String type = itemJson.getString("type");
                this.putItem(id, name, type);
            }

            // Close the streams
            reader.close();


            return 0; // Success
        } catch (IOException e) {
            // Handle IO exception
            Log.e("LoadDatabase", "Error reading JSON file: " + e.getMessage());
        } catch (JSONException e) {
            // Handle JSON exception
            Log.e("LoadDatabase", "Error parsing JSON: " + e.getMessage());
        }
        return -1; // Error
    }
}
