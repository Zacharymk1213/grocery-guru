package edu.qc.seclass.glm;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Set;

public class SearchByTypeActivity extends AppCompatActivity {
    private Button btnBack;
    private ListView listViewTypes;
    private Set<String> typesSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_by_type);

        // Initialize views
        btnBack = findViewById(R.id.btn_back);
        listViewTypes = findViewById(R.id.listView_search_results);

        // Set click listener for the back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the activity and go back to the previous screen
                finish();
            }
        });

        // Load types from the database
        loadTypes();

        // Set up the ListView
        ArrayAdapter<String> typesAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, new ArrayList<>(typesSet));
        listViewTypes.setAdapter(typesAdapter);
    }

    /**
     * Load types from the database
     */
    private void loadTypes() {
        typesSet = ListOfType.loadTypesFromJSON(getApplicationContext());
    }
}


