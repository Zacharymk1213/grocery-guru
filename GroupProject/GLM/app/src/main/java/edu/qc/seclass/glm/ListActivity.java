package edu.qc.seclass.glm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {
    private GroceryList thisList;
    private GroceryItem[] thisListItems;

    //GUI components
    private Button btnBackMocklist, btnSearchItemMocklist, btnSearchTypeMocklist;
    private TextView tvNameMocklist;
    private ListView listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mock_list);
        //get passed data
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            thisList = extras.getParcelable("groceryList"); //key must match what was put in other activity
        thisListItems = thisList.getItems();
        displayItems();

        initializeUI();

        setupListeners();
    }

    private void initializeUI() {
        btnBackMocklist = findViewById(R.id.btn_back_mocklist);
        btnSearchItemMocklist = findViewById(R.id.btn_search_item_mocklist);
        btnSearchTypeMocklist = findViewById(R.id.btn_search_type_mocklist);
        listItems = findViewById(R.id.list_of_items);

        tvNameMocklist = findViewById(R.id.tv_name_mocklist);

    }

    private void setupListeners() {
        btnBackMocklist.setOnClickListener(v -> finish());

        btnSearchItemMocklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Search Item activity
                startActivity(new Intent(ListActivity.this, SearchItemActivity.class));
            }
        });
        btnSearchTypeMocklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Search Type activity
                startActivity(new Intent(ListActivity.this, SearchByTypeActivity.class));
            }
        });

        //listener for each item
        listItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>adapter, View v, int position, long id){
                GroceryItem clickedItem = thisListItems[position];

                // Launch the ListActivity with the clicked list name
                Intent intent = new Intent(ListActivity.this, ListEntryActivity.class);
                // pass data to Intent
                Bundle clickedData = new Bundle(); //make a bundle for our data
                clickedData.putParcelable("groceryItem", clickedItem); //key to be used in ListEntryActivity
                intent.putExtras(clickedData); //pass the bundle along
                startActivity(intent);
            }
        });
    }

    //called automatically when user moved away from this activity
    //(i.e. homepage is not on the screen any more)
    @Override
    protected void onPause() {
        super.onPause();
    }

    //called automatically when user returns to this activity
    @Override
    protected void onResume() {
        super.onResume();
        //refresh myLists
        thisListItems = thisList.getItems();
        displayItems();
    }

    private void displayItems() {
        if (thisList == null)
            return;
        // Get item names from thisListItems
        String[] itemNames = new String[thisListItems.length];
        for (int i = 0; i < thisListItems.length; i++)
            itemNames[i] = thisListItems[i].getName();
        // Create an ArrayAdapter with the item names
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
            ListActivity.this, android.R.layout.simple_list_item_1, itemNames);
        // Set the adapter
        listItems.setAdapter(listAdapter);
    }
}