package edu.qc.seclass.glm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Set;

public class SearchByTypeActivity extends AppCompatActivity {
    
    private GroceryList openedList;

    //GUI components
    private Button btnBack;
    private ListView listViewTypes;
    private Set<String> typesSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_by_type);

        //get the open list if we have one
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            //string key must match what was put in other activity
            openedList = extras.getParcelable("openedList");

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

        // Set item click listener for the listViewTypes
        listViewTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedType = (String) parent.getItemAtPosition(position);

                // Launch the ListActivity with the clicked list name
                Intent intent = new Intent(SearchByTypeActivity.this, ListByTypeActivity.class);
                intent.putExtra("listType", clickedType); //pass the type
                if (openedList != null) //pass the opened list if there is one
                    intent.putExtra("openedList", openedList);
                startActivity(intent);
            }
        });

        // Display types
        refreshTypeList();
    }


    //called automatically when user returns to this activity
    @Override
    protected void onResume() {
        super.onResume();
        refreshTypeList();
    }

    private void refreshTypeList() {
        // get types from the database
        getTypesInDatabase();
        // Set up the ListView
        ArrayAdapter<String> typesAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, new ArrayList<>(typesSet));
        listViewTypes.setAdapter(typesAdapter);
    }

    /**
     * get types from the database
     */
    private void getTypesInDatabase() {
        //typesSet = ListOfType.loadTypesFromJSON(getApplicationContext());
        typesSet = GroceryDatabase.getInstance().getTypes(); //avoid I/O as much as possible
    }
}


