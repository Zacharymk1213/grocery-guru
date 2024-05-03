package edu.qc.seclass.glm;

import java.util.ArrayList;

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
    private ArrayList<GroceryItem> thisListItems;
    private boolean checkedAll = false;

    //GUI components
    private Button btnBackList, btnSearchItemList, btnSearchTypeList, btnCheckAll;
    private TextView tvNameList;
    private ListView listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mock_list);
        //get passed data
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            thisList = extras.getParcelable("groceryList"); //key must match what was put in other activity

        initializeUI();

        setupListeners();

        //must be after initializeUI()
        thisListItems = thisList.getItems();
        displayItems();
        tvNameList.setText(thisList.getName());
        btnCheckAll.setText("Check All");
    }

    private void initializeUI() {
        btnBackList = findViewById(R.id.btn_back_List);
        btnSearchItemList = findViewById(R.id.btn_search_item_List);
        btnSearchTypeList = findViewById(R.id.btn_search_type_List);
        btnCheckAll  = findViewById(R.id.button_check);
        listItems = findViewById(R.id.list_of_items);

        tvNameList = findViewById(R.id.tv_name_List);

    }

    private void setupListeners() {
        btnBackList.setOnClickListener(v -> finish());

        btnSearchItemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Search Item activity
                startActivity(new Intent(ListActivity.this, SearchItemActivity.class));
            }
        });
        btnSearchTypeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Search Type activity
                startActivity(new Intent(ListActivity.this, SearchByTypeActivity.class));
            }
        });
        btnCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkedAll) { //uncheck everything
                    for (int i = 0; i < thisListItems.size(); i++)
                        thisListItems.get(i).setSelected(false);
                    btnCheckAll.setText("Check All");
                    checkedAll = false;
                }
                else { //check everything
                    for (int i = 0; i < thisListItems.size(); i++)
                        thisListItems.get(i).setSelected(true);
                    btnCheckAll.setText("Uncheck All");
                    checkedAll = true;
                }
                //refresh
                displayItems();
            }
        });

        //listener for each item
        listItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>adapter, View v, int position, long id){
                GroceryItem clickedItem = thisListItems.get(position);

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
        //save user data! User only!
        User.getInstance().saveUserData(getApplicationContext());
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
        if (thisListItems == null || listItems == null)
            return;
        // Create an GroceryItemAdapter
        GroceryItemAdapter listsAdapter = new GroceryItemAdapter(ListActivity.this, thisListItems);
        // Set the adapter for the ListView
        listItems.setAdapter(listsAdapter);
    }
}