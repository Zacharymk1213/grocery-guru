package edu.qc.seclass.glm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchItemActivity extends AppCompatActivity {

    private GroceryList openedList;
    private ArrayList<GroceryItem> result;

    //GUI components
    private EditText editTextSearch;
    private Button btnSearch,
                btnAddSelected,
                btnBack,
                btnAddEntry;
    private ListView listViewSearchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_by_name);

        //get the open list if we have one
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            //string key must match what was put in other activity
            openedList = extras.getParcelable("openedList");

        // Initialize views
        editTextSearch = findViewById(R.id.et_search);
        btnBack = findViewById(R.id.btn_back);
        btnAddSelected = findViewById(R.id.btn_add_selected);
        btnSearch = findViewById(R.id.btn_search);
        btnAddEntry = findViewById(R.id.btn_add_entry);
        listViewSearchResults = findViewById(R.id.listView_search_results);

        //if there is no list open(i.e. we came from main activity instead of a user list)
        //make btnAddSelected invisible and disabled
        if (openedList == null)
            btnAddSelected.setVisibility(View.INVISIBLE);
        btnAddSelected.setEnabled(openedList != null);

        // Set click listener for the search button
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered search query
                String searchQuery = editTextSearch.getText().toString().trim();
                if (!searchQuery.isEmpty()) {
                    // Perform search operation (you can define your search logic here)
                    // For demonstration purposes, create a list of dummy search results
                    
                    // Search database
                    result = GroceryDatabase.getInstance().searchItemsByName(searchQuery);
                    if (result == null)
                        Toast.makeText(SearchItemActivity.this,
                            "No results found",
                            Toast.LENGTH_SHORT).show();
                    displaySearchResults();
                } else {
                    // Show an error message if search query is empty
                    editTextSearch.setError("Search query cannot be empty");
                }
            }
        });

        if (btnAddSelected.isEnabled()) {
            btnAddSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean anySelected = false;
                    boolean anyAdded = false;
                    if (result == null) {
                        Toast.makeText(SearchItemActivity.this,
                            "No item selected",
                            Toast.LENGTH_SHORT).show();
                        return; //avoid null ref
                    }
                    for (int i = 0; i < result.size(); i++) {
                        GroceryItem thisItem = result.get(i);
                        if(thisItem.isSelected()) {
                            anySelected = true;
                            //add if openedList don't already have this item
                            if (openedList.getItem(thisItem.getId()) == null) {
                                anyAdded = true;
                                thisItem.setSelected(false);
                                openedList.addItem(thisItem);
                            }
                            else
                                Toast.makeText(SearchItemActivity.this,
                                    thisItem.getName() + " is already in " + openedList.getName(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    if (!anySelected)
                        Toast.makeText(SearchItemActivity.this,
                            "No item selected",
                            Toast.LENGTH_SHORT).show();
                    if (anyAdded) {
                        // change has been made, save!
                        User.getInstance().saveUserData(getApplicationContext());
                        Toast.makeText(SearchItemActivity.this,
                            "Added Successfully",
                            Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        // Set click listener for the back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the activity and go back to the previous screen
                finish();
            }
        });

        //set click listener to add new entry to database
        btnAddEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to adding database entry activity
                startActivity(new Intent(SearchItemActivity.this, NewEntryActivity.class));
            }
        });
    }

    /**
     * Displays the search <b>result</b> to <b>listViewSearchResults</b>
     */
    private void displaySearchResults() {
        if (result == null || listViewSearchResults == null)
            return;
        // Create an GroceryItemAdapter
        GroceryItemAdapter resultAdapter = new GroceryItemAdapter(SearchItemActivity.this, result);
        // Set the adapter for the ListView
        listViewSearchResults.setAdapter(resultAdapter);
    }
}
