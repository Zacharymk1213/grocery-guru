package edu.qc.seclass.glm;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

public class ListActivity extends AppCompatActivity {
    private GroceryList thisList;
    private ArrayList<GroceryItem> thisListItems;
    private boolean checkedAll = false;

    //GUI components
    private EditText etNameList;
    private Button btnBackList, btnDeleteChecked, btnCheckAll,
        btnSearchItemList, btnSearchTypeList;
    private ListView lvListItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);
        //get passed data
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            thisList = extras.getParcelable("groceryList"); //key must match what was put in other activity

        initializeUI();

        setupListeners();

        //must be after initializeUI()
        etNameList.setText(thisList.getName());
        btnCheckAll.setText("Check All");
    }

    private void initializeUI() {
        etNameList = findViewById(R.id.et_name_List);
        btnBackList = findViewById(R.id.btn_back_List);
        btnDeleteChecked = findViewById(R.id.btn_delete_checked);
        btnCheckAll = findViewById(R.id.button_check);
        btnSearchItemList = findViewById(R.id.btn_search_item_List);
        btnSearchTypeList = findViewById(R.id.btn_search_type_List);
        lvListItems = findViewById(R.id.list_of_items);
    }

    private void setupListeners() {
        btnBackList.setOnClickListener(v -> {
            //save any name change
            thisList.setName(etNameList.getText().toString());
            //save file
            User.getInstance().saveUserData();
            finish();
        });
        btnDeleteChecked.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDelete = new AlertDialog.Builder(ListActivity.this);
                alertDelete.setTitle("Delete All Checked Items");
                alertDelete.setMessage("Are you sure you want to delete these items?");
                alertDelete.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //important: must be from last to first since we are doing remove()
                        for (int i = thisListItems.size()-1; i >= 0; i--)
                        if (thisListItems.get(i).isSelected())
                            thisListItems.remove(i);
                        //refresh
                        displayItems();
                        //save file
                        User.getInstance().saveUserData();
                    }
                });
                alertDelete.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // close dialog
                        dialog.cancel();
                    }
                });
                alertDelete.show();
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
                //save file
                User.getInstance().saveUserData();
            }
        });
        
        // add items by item
        btnSearchItemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Search Item activity
                Intent intent = new Intent(ListActivity.this, SearchItemActivity.class);
                intent.putExtra("openedList", thisList); // pass the list
                startActivity(intent);
            }
        });
        // add items by type
        btnSearchTypeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Search Type activity
                Intent intent = new Intent(ListActivity.this, SearchByTypeActivity.class);
                intent.putExtra("openedList", thisList); // pass the list
                startActivity(intent);
            }
        });

        //listener for each item
        lvListItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>adapter, View v, int position, long id){
                GroceryItem clickedItem = thisListItems.get(position);

                // Launch the ListActivity with the clicked list name
                Intent intent = new Intent(ListActivity.this, ListEntryActivity.class);
                // pass data to Intent
                // string key to be used in ListEntryActivity
                intent.putExtra("openedList", thisList); //pass this list
                intent.putExtra("groceryItem", clickedItem); //pass the item
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
        //User.getInstance().saveUserData();
        // ^we don't need this since everything is saved immediately after
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
        if (thisListItems == null || lvListItems == null)
            return;
        // Create an GroceryItemAdapter
        GroceryItemAdapter listsAdapter = new GroceryItemAdapter(ListActivity.this, thisListItems, thisList);
        // Set the adapter for the ListView
        lvListItems.setAdapter(listsAdapter);
    }
}