package edu.qc.seclass.glm;
import java.util.TreeMap;

/**
 * The User class utilizes a binary tree to store its grocery lists
 * @author Jiafeng Lin
 */
public class User {
    private String name;
    private TreeMap<Integer, GroceryList> lists;

    //constructor
    public User(String n) {
        name = n;
        lists = new TreeMap<>();
    }

    //access methods
    public String getName() { return name; }
    public void setName(String n) { name = n; }

    public GroceryList getList(int listID) {
        return lists.get(listID);
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
        lists.get(listID).setSeleted(true);
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
    private GroceryItem createDatabaseEntry(String name, String type) {
        // Implement creating and returning a new GroceryItem
        // For example:
        // GroceryItem newItem = new GroceryItem(name, type);
        // return newItem;
        return null; // Placeholder, replace with actual implementation
    }

    /**
     * Search <b>name</b> in database entries and get back items of similar name
     * @param name
     * @return an array of grocery items with similar name
     */
    public GroceryItem[] lookUpItem(String name) {
        // Implement searching the database for items with similar name
        // For example:
        // GroceryItem[] items = database.searchItemsByName(name);
        // return items;
        return null; // Placeholder, replace with actual implementation
    }

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
    public JSONObject getJSONObject() {
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
}