package edu.qc.seclass.glm;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;
import org.json.JSONException;
import java.io.IOException;

import java.util.TreeMap;
import java.util.Set;

/**
 * The User class utilizes a binary tree to store its grocery lists <p>
 * This is a singleton class: only static instance,
 * so the sole user object and its methods can be accessed in any activity
 * @author Jiafeng Lin
 */
public class User {
    private String name;
    private  TreeMap<Integer, GroceryList> lists;
    private static User instance = null;

    //constructor
    private User() {
        name = "";
        lists = new TreeMap<>();
    }
    public static User getInstance() {
       if(instance == null) {
          instance = new User();
       }
       return instance;
    }

    //access methods
    public String getName() { return name; }
    public void setName(String n) { name = n; }

    public GroceryList getList(int listID) {
        return lists.get(listID);
    }
    
    /*
     * Returns all lists
     */
    public  String[] getListNames() {
        String[] tem = new String[lists.size()];
        int i = 0;
        Set<Integer> listIDs = lists.keySet();
        for (int id : listIDs)
            tem[i++] = (lists.get(id)).getName();
        return tem;
    }

    /**
     * Adds a new grocery list for the user
     * @param listName
     */
    public void createList(String listName) {
        GroceryList l = new GroceryList(listName);
        lists.put(l.getId(), l);
    }

    /**
     * Adds an existing list
     * @param list
     */
    public void addList(GroceryList list) {
        lists.put(list.getId(), list);
    }

    /**
     * Removes a list of matching ID for the user
     * @param listID
     * @return the list deleted
     */
    public GroceryList deleteList(int listID) {
        return lists.remove(listID);
    }

    /**
     * Mark the list as selected, should be called from an on_click
     * @param listID
     */
    public void selectList(int listID) {
        lists.get(listID).setSelected(true);
    }

    /**
     * Open the GUI page of a grocery list
     * @param listID
     */
    public void openList(int listID) {
        // Implement opening GUI page for the grocery list with listID
        // For example:
        // Intent intent = new Intent(context, GroceryListActivity.class);
        // intent.putExtra("listID", listID);
        // context.startActivity(intent);
    }

    /**
     * Rename the list of matching ID to <b>listName</b>
     * @param listID
     * @param listName
     */
    public void renameList(int listID, String listName) {
        lists.get(listID).setName(listName);
    }

    /**
     * Adds a new item entry to the database
     * should only be called from inside lookUpItem() when no item is found
     * @param name
     * @param type
     * @return a copy of the newly created item
     */
    //private GroceryItem createDatabaseEntry(int id,String name, String type, GroceryDatabase db) {
    //    return db.putItem(id,name, type);
    //}

    /**
     * Search <b>name</b> in database entries and get back items of similar name
     * @param name
     * @return an array of grocery items with similar name
     */
    //public GroceryItem[] lookUpItem(String name, GroceryDatabase db) {
        //while user has a list page open
        //listen for when user typed something into searchBarView
        //get String n from searchBarView
        //pass n into searchItemsByName(n)
        //get items from database
     //   GroceryItem[] items = db.searchItemsByName(name);
        //display result to user in a searchResultDropList right below searchBarView
        //if user click on an item in searchResultDropList
        //add clicked item to *this* open list

   // }

    /**
     * Get from the database a copied list of all entries of <b>type</b>;
     * open the GUI page for the new ListOfType
     * @param type
     * @return a ListOfType containing all entries of the specified type
     */
    public ListOfType getListOfType(String type) {
        // Implement fetching entries from the database and returning a ListOfType
        // For example:
        // ListOfType list = database.getListOfType(type);
        // openList(list.getId());
        // return list;
        return null; // Placeholder, replace with actual implementation
    }

    /**
     * Returns a JSONObject containing user name and all lists such that <p>
     * "name" : name <p>
     * "list" : { <p>
     *     "listID" : listObject <p>
     *     "listID" : listObject <p>
     * }
     * @return
     */
    public JSONObject getJSONObject() throws JSONException {
        JSONObject userJson = new JSONObject();
        userJson.put("name", name);
        //put listObjects
        JSONObject listJson = new JSONObject();
        Set<Integer> listIDs = lists.keySet();
        for (int id : listIDs)
            listJson.put(""+id, lists.get(id).getJSONObject());
        userJson.put("list", listJson);
        return userJson;
    }

    /**
     * save all user data, including their grocery lists, to user_data.json
     * @return 0 if save is successful
     */
    public int saveUserData(Context context) {
        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(context.openFileOutput("user_data.json", 0))
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
     * load all user data, including their grocery lists, from user_data.json
     * @return 0 if load is successful
     */
    public int loadUserData(Context context) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(context.openFileInput("user_data.json"))
                )) {
            StringBuilder jsonData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }

            // Parse JSON data
            JSONObject userDataJson = new JSONObject(jsonData.toString());

            // Load user data
            //User owner = User.getInstance();
            this.setName(userDataJson.getString("name"));
            GroceryDatabase database = GroceryDatabase.getInstance();
            // load the lists
            JSONObject userLists = userDataJson.getJSONObject("list");
            Iterator<String> lists = userLists.keys();
            while (lists.hasNext()) {
                JSONObject jList = userLists.getJSONObject(lists.next());
                GroceryList gList = new GroceryList(jList.getInt("id"), jList.getString("name"));
                gList.setSelected(jList.getBoolean("isSelected"));
                this.addList(gList);
                //load items of this list
                JSONObject listItems = jList.getJSONObject("list");
                Iterator<String> items = listItems.keys();
                while (items.hasNext()) {
                    JSONObject jItem = listItems.getJSONObject(items.next());
                    //this item better be in the database, or else something went wrong
                    GroceryItem gItem = database.copyItem(jItem.getInt("id"));
                    gItem.updateQuantity(jItem.getInt("quantity"));
                    gList.addItem(gItem);
                }
            }

            // Close the streams
            reader.close();

            return 0; // Success
        } catch (IOException e) {
            // Handle IO exception
            Log.e("LoadUserData", "Error reading JSON file: " + e.getMessage());
        } catch (JSONException e) {
            // Handle JSON exception
            Log.e("LoadUserData", "Error parsing JSON: " + e.getMessage());
        }
        return -1; // Error
    }
}