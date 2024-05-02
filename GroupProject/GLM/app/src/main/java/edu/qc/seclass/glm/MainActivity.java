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
    //GUI components
    ListView myLists;
    Button btnCreateNewList, btnSearchItem, btnSearchType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //test
        //GroceryDatabase db = GroceryDatabase.getInstance(); //enable these lines to reset app data
        //db.putItem(1, "Apple", "Fruit");
        //db.putItem(2, "Banana", "Fruit");
        //db.putItem(3, "Carrot", "Vegetable");
        //db.putItem(4, "Broccoli", "Vegetable");
        //db.putItem(5, "Milk", "Dairy");
        //User owner = User.getInstance(); //enable
        //GroceryList l1 = new GroceryList(1, "Test List 1");
        //l1.addItem(db.copyItem(1));
        //l1.addItem(db.copyItem(2));
        //GroceryList l2 = new GroceryList(2, "Mylist 2");
        //l1.addItem(db.copyItem(5));
        //l1.addItem(db.copyItem(3));
        //owner.addList(l1);
        //owner.addList(l2);
        //if (saveAllData(getApplicationContext()) == 0) //enable
        //    Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show(); //enable
        //else //enable
        //    Toast.makeText(this, "Error saving data", Toast.LENGTH_SHORT).show(); //enable
        //end

        //first, load from local drive and previous data into program
        //user data and database should be on different save_file

        // Load user data
        // important: check loaded before calling loadAllData()
        if (loadAllData(getApplicationContext()) != 0)
            // load failed, handle error
            Toast.makeText(this, "Error loading data", Toast.LENGTH_SHORT).show();

        // Get list items
        myLists = findViewById(R.id.my_lists);
        displayList(myLists);
        
        // Find the buttons by their IDs
        btnCreateNewList = findViewById(R.id.btn_create_new_list);
        btnSearchItem = findViewById(R.id.btn_search_item);
        btnSearchType = findViewById(R.id.btn_search_type);

        // Set click listener for list items
        myLists.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>a, View v, int position, long id){
                String clickedListName = (String) a.getItemAtPosition(position);

                // Launch the ListActivity with the clicked list name
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("listName", clickedListName);
                startActivity(intent);
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

    //called automatically when user moved away from main activity
    //(i.e. homepage is not on the screen any more)
    @Override
    protected void onPause() {
        super.onPause();
        //user might've exited app, save data!
        saveAllData(getApplicationContext());
    }

    //called automatically when user returns to main activity
    @Override
    protected void onResume() {
        super.onResume();
        //refresh myLists
        displayList(myLists);
    }

    /**
     * load all user data, their grocery lists, and database from drive. <p>
     * user data and database should be stored in two separate files
     * @return 0 if load is successful
     */
    private int loadAllData(Context context) {
        // Must load database before user data
        int err = GroceryDatabase.getInstance().loadDatabase(context);
        err += User.getInstance().loadUserData(context);
        return err;
    }

    /**
     * saves all user data, their grocery lists and database to drive. <p>
     * user data and database should be stored in two separate files
     * @return 0 if save is successful
     */
    private int saveAllData(Context context) {
        int err = GroceryDatabase.getInstance().saveDatabase(context);
        err += User.getInstance().saveUserData(context);
        return err;
    }

    /**
     * Display any changes made to user lists to <b>lView</b>
     * @param lView
     */
    private void displayList(ListView lView) {
        // Get the instance of User
        User user = User.getInstance();
        // Get list names from the User instance
        String[] listNames = user.getListNames();
        // Create an ArrayAdapter with the list names
        ArrayAdapter<String> listsAdapter = new ArrayAdapter<String>(
            MainActivity.this, android.R.layout.simple_list_item_1, listNames);
        // Set the adapter for the ListView
        lView.setAdapter(listsAdapter);
    }
}