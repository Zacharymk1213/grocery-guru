package edu.qc.seclass.glm;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //first, load from local drive and previous data into program
        //use relative path
        //user data and database should be on different save_file
        // Get list items
        ListView myLists = findViewById(R.id.my_lists);
        // Load user data
        int userDataLoadResult = loadAllData(getApplicationContext()); // Pass the context

        if (userDataLoadResult == 0) {
            // User data loaded successfully
            // display myLists
            refreshList(myLists);
        } else {
            // Handle error while loading user data
            // For example, display an error message to the user
            Toast.makeText(this, "Error loading user data", Toast.LENGTH_SHORT).show();
        }



        
        // Find the buttons by their IDs
        Button btnCreateNewList = findViewById(R.id.btn_create_new_list);
        Button btnSearchItem = findViewById(R.id.btn_search_item);
        Button btnSearchType = findViewById(R.id.btn_search_type);

        // Set click listener for list items
        myLists.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>a, View v, int position, long id){
                //Intent intent = new Intent(MainActivity.this, listActivity.class);
                //startActivity(intent);
            }
        });

        // Set click listeners for the buttons
        btnCreateNewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the Create New List activity
                Intent intent = new Intent(MainActivity.this, CreateNewListActivity.class);
                startActivity(intent);
            }
        });
        btnSearchItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Search Item activity
                startActivity(new Intent(MainActivity.this, SearchItemActivity.class));
            }
        });
        btnSearchType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Search Type activity
                startActivity(new Intent(MainActivity.this, SearchByTypeActivity.class));
            }
        });
    }

    /**
     * load all user data, their grocery lists, and database from drive. <p>
     * user data and database should be stored in two separate files
     * @return 0 if load is successful
     */
    public int loadAllData(Context context) {
        // Must load database before user data
        int err = loadDatabase(context);
        err += loadUserData();
        return err;
    }


    /**
     * load all grocery item entries in database from item_database.json
     * @return 0 if load is successful
     */
    public int loadDatabase(Context context) {
        try {
            // Get InputStream for the JSON file from the assets folder
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("item_database.json");

            // Create a InputStreamReader and BufferedReader to read the JSON data
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            // Read JSON data line by line and append to StringBuilder
            StringBuilder jsonData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }

            // Parse JSON data
            JSONObject dbJson = new JSONObject(jsonData.toString());

            // Load database
            GroceryDatabase database = GroceryDatabase.getInstance();
            Iterator<String> entries = dbJson.keys();
            while (entries.hasNext()) {
                String key = entries.next();
                JSONObject itemJson = dbJson.getJSONObject(key);
                int id = itemJson.getInt("id");
                String name = itemJson.getString("name");
                String type = itemJson.getString("type");
                GroceryItem item = new GroceryItem(id, name, type);
                database.putItem(id, name, type);
            }

            // Close the streams
            reader.close();
            inputStreamReader.close();
            inputStream.close();

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

    /**
     * load all user data, including their grocery lists, from user_data.json
     * @return 0 if load is successful
     */


    public int loadUserData() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("user_data.json")))) {
            StringBuilder jsonData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }

            // Parse JSON data
            JSONObject userDataJson = new JSONObject(jsonData.toString());

            // Load user data
            User owner = User.getInstance();
            owner.setName(userDataJson.getString("name"));
            GroceryDatabase database = GroceryDatabase.getInstance();
            // load the lists
            JSONObject userLists = userDataJson.getJSONObject("list");
            Iterator<String> lists = userLists.keys();
            while (lists.hasNext()) {
                JSONObject jList = userLists.getJSONObject(lists.next());
                GroceryList gList = new GroceryList(jList.getInt("id"), jList.getString("name"));
                gList.setSelected(jList.getBoolean("isSelected"));
                owner.addList(gList);
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




    /**
     * saves all user data, their grocery lists and database to drive. <p>
     * user data and database should be stored in two separate files
     * @return 0 if save is successful
     */
    public int saveAllData() {
        int err = saveDatabase();
        err += saveUserData();
        return err;
    }
    
    /**
     * save all grocery item entries in database to item_database.json
     * @return 0 if save is successful
     */
    public int saveDatabase() {
        try (FileWriter fileWriter = new FileWriter("item_database.json")) {
            // Create JSON object for database
            JSONObject dbJson = GroceryDatabase.getInstance().getJSONObject();
            // Write JSON data to file
            fileWriter.write(dbJson.toString());
            return 0; // Success
        } catch (IOException e) {
            // Handle IO exception
            Log.e("SaveDatabase", "Error writing JSON file: " + e.getMessage());
        } catch (JSONException e) {
            // Handle JSON exception
            Log.e("SaveDatabase", "Error creating JSON: " + e.getMessage());
        }
        return -1; // Error
    }

    /**
     * save all user data, including their grocery lists, to user_data.json
     * @return 0 if save is successful
     */
    public int saveUserData() {
        try (FileWriter fileWriter = new FileWriter("user_data.json")) {
            // Create JSON object for user data
            JSONObject userDataJson = User.getInstance().getJSONObject();
            // Write JSON data to file
            fileWriter.write(userDataJson.toString());
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
     * Refreshes the display to show any changes made to user lists
     */
    public void refreshList(ListView lv) {
        // Get the instance of User
        User user = User.getInstance();
        // Get list names from the User instance
        String[] listNames = user.getListNames();
        // Create an ArrayAdapter with the list names
        ArrayAdapter<String> listsAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, listNames);
        // Set the adapter for the ListView
        lv.setAdapter(listsAdapter);
    }
}