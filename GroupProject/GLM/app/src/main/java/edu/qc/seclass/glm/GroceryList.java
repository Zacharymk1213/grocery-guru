package edu.qc.seclass.glm;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.LinkedHashMap;
import java.util.Set;

import org.json.JSONObject;
import org.json.JSONException;

/**
 * The grocery list represents a user's list of to-buy groceries, it
 * uses a {@link LinkedHashMap } to store its set of grocery items
 * @author Jiafeng Lin
 */
public class GroceryList implements Parcelable {
    private static int idCount;
    private int id;
    private String name;
    private LinkedHashMap<Integer, GroceryItem> list; //should not be static
    private boolean isSelected = false;

    //constructor
    public GroceryList(String n) {
        id = idCount++;
        name = n;
        list = new LinkedHashMap<>();
        // Assign the context
    }
    //for cloning an old list from userdata; hence id is known
    public GroceryList(int d, String n) {
        if (d >= idCount)
            idCount = d+1;
        id = d;
        name = n;
        list = new LinkedHashMap<>();
    }

    //access methods
    public int getId() { return id; }
    //setId() is an invalid operation

    public String getName() { return name; }
    public void setName(String n) { name = n; }

    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean tf) { isSelected = tf; }

    public LinkedHashMap<Integer, GroceryItem> getList() { return list; }

    /**
     * Return the item from grocery list if it exists
     * @param id
     * @return the item of matching id
     */
    public GroceryItem getItem(int id) {
        return list.get(id);
    }

    /**
     * @return all the items in list through an array
     */
    public GroceryItem[] getItems() {
        GroceryItem[] items = new GroceryItem[list.size()];
        Set<Integer> itemIDs = list.keySet();
        int i = 0;
        for (int id : itemIDs)
            items[i++] = list.get(id);
        return items;
    }

    /**
     * Adds an item to the grocery list
     * @param item
     */
    public void addItem(GroceryItem item) {
        list.put(item.getId(), item);
    }
    
    /**
     * Removes item of ID from grocery list and returns it
     * @param itemID
     * @return the <b>item</b> deleted
     */
    public GroceryItem deleteItem(int itemID) {
        return list.remove(itemID);
    }
    
    /**
     * Change the quantity of item of ID;
     * postive <b>amount</b> to increase, negative <b>amount</b> to decrease
     * @param itemID
     * @param amount
     */
    public void changeQuantity(int itemID, int amount) {
        getItem(itemID).updateQuantity(amount);
    }

    /**
     * Mark the grocery item on list as checkedOff
     * @param itemID
     */
    public void checkOff(int itemID) {
        GroceryItem item = getItem(itemID);
        if (item != null) {
            item.setSelected(true);
        }
        // No toast message shown when the item is not found in the list
    }

    /**
     * Iterates through the entire list, clear off their selection
     */
    public void clearCheckOff() {
        Set<Integer> itemIDs = list.keySet();
        for (int id : itemIDs)
            list.get(id).setSelected(false);
    }

    public String toString() {
        return "{" + id + ", " + name + ", " + isSelected + "}";
    }

    /**
     * Returns a JSONObject containing list data such that <p>
     * "id" : id <p>
     * "name" : name <p>
     * "isSelected" : false/true <p>
     * "list" : { <p>
     *     "itemID" : itemObject <p>
     *     "itemID" : itemObject <p>
     * }
     * @return
     */
    public JSONObject getJSONObject() throws JSONException {
        JSONObject listJson = new JSONObject();
        listJson.put("id", id);
        listJson.put("name", name);
        listJson.put("isSelected", isSelected);
        JSONObject itemsJson = new JSONObject();
        //put listObjects
        Set<Integer> itemIDs = list.keySet();
        for (int id : itemIDs)
            itemsJson.put(""+id, list.get(id).getJSONObject());
        listJson.put("list", itemsJson);
        return listJson;
    }

    
    //----------------Parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(name);
        out.writeBoolean(isSelected);
    }

    public static final Parcelable.Creator<GroceryList> CREATOR = new Parcelable.Creator<GroceryList>() {
        public GroceryList createFromParcel(Parcel in) {
            return new GroceryList(in);
        }

        public GroceryList[] newArray(int size) {
            return new GroceryList[size];
        }
    };

    // constructor that takes a Parcel and gives you an object populated with it's values
    private GroceryList(Parcel in) {
        id = in.readInt();
        name = in.readString();
        list = User.getInstance().getGroceryList(id).getList();
        isSelected = in.readBoolean();
    }
}
