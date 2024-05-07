package edu.qc.seclass.glm;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

public class MainActivity extends AppCompatActivity {

    private ArrayList<GroceryList> userLists;

    //GUI components
    private ListView myLists;
    private ImageButton ibtnAppManual;
    private Button btnCreateNewList, btnSearchItem, btnSearchType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize db and user, give them context once and for all
        GroceryDatabase.getInstance().setContext(getApplicationContext());
        User.getInstance().setContext(getApplicationContext());
        
        //load data, must load context into db and user first
        if (loadAllData() != 0)
            // load failed, handle error
            Toast.makeText(this, "Error loading data, first time?", Toast.LENGTH_SHORT).show();
            
        // Get list items
        myLists = findViewById(R.id.my_lists);

        ibtnAppManual = findViewById(R.id.ibtn_app_manual);
        
        // Find the buttons by their IDs
        btnCreateNewList = findViewById(R.id.btn_create_new_list);
        btnSearchItem = findViewById(R.id.btn_search_item);
        btnSearchType = findViewById(R.id.btn_search_type);

        // Set click listener for list items
        myLists.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>adapter, View v, int position, long id) {
                GroceryList clickedList = userLists.get(position);

                // Launch the ListActivity with the clicked list name
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                // pass data to Intent
                intent.putExtra("groceryList", clickedList); //pass the bundle along

                startActivity(intent);
            }
        });

        ibtnAppManual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open the AppManual activity
                startActivity(new Intent(MainActivity.this, AppManualActivity.class));
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

        // first time entering app
        if (GroceryDatabase.getInstance().isEmpty() && User.getInstance().getName().equals("")) {
            AlertDialog.Builder alertHelp = new AlertDialog.Builder(MainActivity.this);
            alertHelp.setTitle("Hey There!");
            alertHelp.setMessage("Welcome to GroceryGuru, this app will help you keep track of all "
                + "the groceries you'll need to buy! Would you like a new list and some basic "
                + "grocery items to get started?");
            alertHelp.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    AppHelper.populate(GroceryDatabase.getInstance());
                    // create a default grocery list
                    User user = User.getInstance();
                    user.createList("New Market Groceries");
                    user.saveUserData();
                    displayList();
                }
            });
            alertHelp.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // close dialog
                    dialog.dismiss();
                }
            });
            alertHelp.show();
        }
    }

    //called automatically when user moved away from main activity
    //(i.e. homepage is not on the screen any more)
    @Override
    protected void onPause() {
        super.onPause();
        //user might've exited app, save data!
        saveAllData();
    }

    //called automatically when user returns to main activity
    @Override
    protected void onResume() {
        super.onResume();
        //refresh myLists
        displayList();
    }

    /**
     * load all user data, their grocery lists, and database from drive. <p>
     * user data and database should be stored in two separate files
     * @return 0 if load is successful
     */
    private int loadAllData() {
        // Must load database before user data
        int err = GroceryDatabase.getInstance().loadDatabase();
        err += User.getInstance().loadUserData();
        return err;
    }

    /**
     * saves all user data, their grocery lists and database to drive. <p>
     * user data and database should be stored in two separate files
     * @return 0 if save is successful
     */
    private int saveAllData() {
        int err = GroceryDatabase.getInstance().saveDatabase();
        err += User.getInstance().saveUserData();
        return err;
    }

    /**
     * Display any changes made to user lists to <b>myLists</b>
     */
    private void displayList() {
        if (myLists == null)
            return;
        userLists = User.getInstance().getLists();
        // Create an GroceryListAdapter
        GroceryListAdapter listsAdapter = new GroceryListAdapter(MainActivity.this, userLists);
        // Set the adapter for the ListView
        myLists.setAdapter(listsAdapter);
    }
}