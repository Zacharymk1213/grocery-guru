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
        int err = GroceryDatabase.getInstance().loadDatabase(context);
        err += User.getInstance().loadUserData(context);
        return err;
    }

    /**
     * saves all user data, their grocery lists and database to drive. <p>
     * user data and database should be stored in two separate files
     * @return 0 if save is successful
     */
    public int saveAllData(Context context) {
        int err = GroceryDatabase.getInstance().saveDatabase(context);
        err += User.getInstance().saveUserData(context);
        return err;
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