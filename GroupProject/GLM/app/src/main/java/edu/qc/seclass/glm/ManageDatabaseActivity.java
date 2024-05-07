package edu.qc.seclass.glm;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

public class ManageDatabaseActivity extends AppCompatActivity {
    
    private ArrayList<GroceryItem> entries;
    private boolean selectedAll = false;

    //GUI components
    private Button btnBack, btnDeleteSelected, btnSelectAll;
    private ListView lvDatabaseEntries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_database);

        initializeUI();

        setupListeners();

        entries = GroceryDatabase.getInstance().getAllItems();
    }

    private void initializeUI() {
        btnBack = findViewById(R.id.btn_back);
        btnDeleteSelected = findViewById(R.id.btn_delete_selected);
        btnSelectAll = findViewById(R.id.btn_select_all);
        lvDatabaseEntries = findViewById(R.id.lv_database_entries);
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> {
            finish();
        });
        btnDeleteSelected.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDelete = new AlertDialog.Builder(ManageDatabaseActivity.this);
                alertDelete.setTitle("Delete All Selected Items");
                alertDelete.setMessage("Are you sure you want to delete these items?");
                alertDelete.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        GroceryDatabase db = GroceryDatabase.getInstance();
                        //important: must be from last to first since we are doing remove()
                        for (int i = entries.size()-1; i >= 0; i--) {
                            GroceryItem thisItem = entries.get(i);
                            if (thisItem.isSelected()) {
                                entries.remove(i);
                                db.remove(thisItem.getId());
                            }
                        }
                        //refresh
                        displayItems();
                        //save file
                        db.saveDatabase();
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
        btnSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedAll) { //uncheck everything
                    for (int i = 0; i < entries.size(); i++)
                        entries.get(i).setSelected(false);
                    btnSelectAll.setText("Select All");
                    selectedAll = false;
                }
                else { //check everything
                    for (int i = 0; i < entries.size(); i++)
                        entries.get(i).setSelected(true);
                    btnSelectAll.setText("Unselect All");
                    selectedAll = true;
                }
                //refresh
                displayItems();
                //save file
                GroceryDatabase.getInstance().saveDatabase();
            }
        });
    }

    //called automatically when user moved away from this activity
    //(i.e. homepage is not on the screen any more)
    @Override
    protected void onPause() {
        super.onPause();
        //make sure everything is unselected
        for (int i = 0; i < entries.size(); i++)
            entries.get(i).setSelected(false);
    }

    //called automatically when user returns to this activity
    @Override
    protected void onResume() {
        super.onResume();
        //refresh myLists
        entries = GroceryDatabase.getInstance().getAllItems();
        displayItems();
    }

    private void displayItems() {
        if (lvDatabaseEntries == null)
            return;
        // Create an GroceryItemAdapter
        GroceryItemAdapter entryAdapter = new GroceryItemAdapter(ManageDatabaseActivity.this, entries);
        // Set the adapter for the ListView
        lvDatabaseEntries.setAdapter(entryAdapter);
    }
}
