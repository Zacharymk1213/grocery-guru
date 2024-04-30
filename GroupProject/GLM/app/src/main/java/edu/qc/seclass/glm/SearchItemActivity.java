package edu.qc.seclass.glm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchItemActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private Button btnSearch;
    private Button btnBack;
    private ListView listViewSearchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_by_name);

        // Initialize views
        editTextSearch = findViewById(R.id.et_search);
        btnSearch = findViewById(R.id.btn_search);
        btnBack = findViewById(R.id.btn_back);
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
                    
                    //we can do this now, not sure how we would display GroceryItem[] though
                    //what adapter do we use? I'm not particular experienced with this...
                    GroceryItem[] items = GroceryDatabase.getInstance().searchItemsByName(searchQuery);
                    
                    ArrayList<String> searchResults = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        searchResults.add("Search Result " + (i + 1));
                    }
                    // Display search results in a ListView (you can customize this part)
                    //SearchResultsAdapter adapter = new SearchResultsAdapter(SearchItemActivity.this, searchResults);
                   //listViewSearchResults.setAdapter(adapter);
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
    }
}
