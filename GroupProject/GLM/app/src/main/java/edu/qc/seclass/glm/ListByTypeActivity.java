package edu.qc.seclass.glm;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ListByTypeActivity extends AppCompatActivity {
    
    private GroceryList openedList;

    private ListOfType thisTypeList;
    private ArrayList<GroceryItem> thisTypeItems;
    private boolean selectedAll = false;

    //GUI components
    private TextView tvType;
    private Button btnBack, btnAddSelected, btnSelectAll;
    private ListView lvTypeItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_by_type);

        //get the open list if we have one
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //string key must match what was put in other activity
            openedList = extras.getParcelable("openedList");
            //get from database the list of this type
            thisTypeList = GroceryDatabase.getInstance().getListOfType(extras.getString("listType"));
            thisTypeItems = thisTypeList.getItems();
        }

        // Initialize views
        tvType = findViewById(R.id.tv_list_type);
        btnBack = findViewById(R.id.btn_back);
        btnAddSelected = findViewById(R.id.btn_add_selected);
        btnSelectAll = findViewById(R.id.btn_select_all);
        lvTypeItems = findViewById(R.id.lv_type_items);

        if (thisTypeList != null)
            tvType.setText(thisTypeList.getType());

        //if there is no list open(i.e. we came from main activity instead of a user list)
        //make btnAddSelected invisible and disabled
        if (openedList == null)
            btnAddSelected.setVisibility(View.INVISIBLE);
        btnAddSelected.setEnabled(openedList != null);

        // Set click listener for the back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the activity and go back to the previous screen
                finish();
            }
        });

        if (btnAddSelected.isEnabled()) {
            btnAddSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean anySelected = false;
                    for (int i = 0; i < thisTypeItems.size(); i++) {
                        GroceryItem thisItem = thisTypeItems.get(i);
                        if(thisItem.isSelected()) {
                            anySelected = true;
                            thisItem.setSelected(false);
                            openedList.addItem(thisItem);
                        }
                    }
                    if (anySelected) {
                        // change has been made, save!
                        User.getInstance().saveUserData();
                        Toast.makeText(ListByTypeActivity.this,
                            "Added Successfully",
                            Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(ListByTypeActivity.this,
                            "No item selected",
                            Toast.LENGTH_SHORT).show();
                    // refresh
                    displayItems();
                }
            });
        }

        // set listenter for select all
        btnSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedAll) { //uncheck everything
                    for (int i = 0; i < thisTypeItems.size(); i++)
                        thisTypeItems.get(i).setSelected(false);
                    btnSelectAll.setText("Select All");
                    selectedAll = false;
                }
                else { //check everything
                    for (int i = 0; i < thisTypeItems.size(); i++)
                        thisTypeItems.get(i).setSelected(true);
                    btnSelectAll.setText("Unselect All");
                    selectedAll = true;
                }
                //refresh
                displayItems();
            }
        });

    }

    //called automatically when user returns to this activity
    @Override
    protected void onResume() {
        super.onResume();
        //refresh myLists
        thisTypeItems = thisTypeList.getItems();
        displayItems();
    }

    /**
     * remove items already in the openList
     */
    private void filterTypeItems() {
        if (openedList == null)
            return;
        //important: must be from last to first since we are doing remove()
        for (int i = thisTypeItems.size()-1; i >= 0; i--)
            if (openedList.getItem(thisTypeItems.get(i).getId()) != null) //is in openList
                thisTypeItems.remove(i);
    }

    private void displayItems() {
        if (thisTypeItems == null || lvTypeItems == null)
            return;
        filterTypeItems();
        // Create an GroceryItemAdapter
        GroceryItemAdapter listsAdapter = new GroceryItemAdapter(ListByTypeActivity.this, thisTypeItems);
        // Set the adapter for the ListView
        lvTypeItems.setAdapter(listsAdapter);
    }
}
