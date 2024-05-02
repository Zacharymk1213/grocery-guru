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

    private EditText editTextSearch;
    private Button btnSearch,
                btnBack,
                btnAddEntry;
    private ListView listViewSearchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_by_name);

        // Initialize views
        editTextSearch = findViewById(R.id.et_search);
        btnSearch = findViewById(R.id.btn_search);
        btnBack = findViewById(R.id.btn_back);
        btnAddEntry = findViewById(R.id.btn_add_entry);
        listViewSearchResults = findViewById(R.id.listView_search_results);

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
                    GroceryItem[] items = GroceryDatabase.getInstance().searchItemsByName(searchQuery);
                    // Display search results in a ListView (you can customize this part)
                    displaySearchResults(listViewSearchResults, items);
                } else {
                    // Show an error message if search query is empty
                    editTextSearch.setError("Search query cannot be empty");
                }
            }
        });

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
     * Displays the search <b>result</b> to <b>lView</b>
     * @param lView
     * @param result
     */
    private void displaySearchResults(ListView lView, GroceryItem[] result) {
        String[] itemNames;
        if (result[0] == null) {
            itemNames = new String[1];
            itemNames[0] = "No Results Found";
        }
        else {
            itemNames = new String[result.length];
            for (int i = 0; i < result.length; i++)
                itemNames[i] = result[i].getName();
        }
        // Create an ArrayAdapter with the item names
        ArrayAdapter<String> resultAdapter = new ArrayAdapter<String>(
            SearchItemActivity.this, android.R.layout.simple_list_item_1, itemNames);
        // Set the adapter for the ListView
        lView.setAdapter(resultAdapter);
    }
}
