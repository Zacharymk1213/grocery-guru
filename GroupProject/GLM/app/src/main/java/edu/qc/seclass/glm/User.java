package edu.qc.seclass.glm;

import java.util.TreeMap;

/**
 * The User class utilizes a binary tree to store its grocery lists
 * @author Jiafeng Lin
 */
public class User {
    //userID ommited, we assume the app only has one user, which is the owner of device
    private String name;
    private TreeMap<Integer, GroceryList> lists;

    //constructor
    public User(String n) {
        name = n;
        lists = new TreeMap<Integer, GroceryList>();
    }

    //access methods
    public String getName() { return name; }
    public void setName(String n) { name = n; }

    /**
     * Adds a new grocery list for the user
     * @param listName
     */
    public void createList(String listName) {
        GroceryList l = new GroceryList(listName);
        lists.put(l.getId(), l);
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
     * @return the a copy of newly created item
     */
    private GroceryItem createDatabaseEntry(String name, String type) {
    }

    /**
     * Search <b>name</b> in database entries and get back items of similar name
     * @return an array of grocery items with similar name
     */
    public GroceryItem[] lookUpItem(String name) {
    }

    /**
     * Get from the database a copied list of all entries of <b>type</b>;
     * open the GUI page for the new ListOfType
     * @param type
     * @return
     */
    public ListOfType getListOfType(String type) {
    }
}