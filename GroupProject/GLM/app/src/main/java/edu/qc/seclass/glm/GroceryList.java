package edu.qc.seclass.glm;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
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

    public LinkedHashMap<Integer, GroceryItem> getList() { return list; }

    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean tf) { isSelected = tf; }

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
    public ArrayList<GroceryItem> getItems() {
        sortListByType();
        return new ArrayList<GroceryItem>(list.values());
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
     * sorts the grocery list by typing as appears in database
     */
    public void sortListByType() {
        LinkedHashMap<Integer, GroceryItem> result = new LinkedHashMap<>();
        Set<String> typesSet = GroceryDatabase.getInstance().getTypes();
        Set<Integer> itemIDs = list.keySet();
        for (String type : typesSet)
            for (int id : itemIDs) {
                GroceryItem thisItem = list.get(id);
                if (thisItem.getType().equals(type))
                    result.put(id, thisItem); //move to the back of new list
            }

        list = result;
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
        //out.writeBoolean(isSelected);
    }

    public static final Parcelable.Creator<GroceryList> CREATOR = new Parcelable.Creator<GroceryList>() {
        public GroceryList createFromParcel(Parcel in) {
            int id = in.readInt();
            GroceryList l = User.getInstance().getGroceryList(id);
            if (l == null)
                return new GroceryList(in);
            else
                return l;
        }

        public GroceryList[] newArray(int size) {
            return new GroceryList[size];
        }
    };

    // constructor that takes a Parcel and gives you an object populated with it's values
    private GroceryList(Parcel in) {
        id = in.readInt();
        name = in.readString();
        list = new LinkedHashMap<>();
        isSelected = false;
    }
}
