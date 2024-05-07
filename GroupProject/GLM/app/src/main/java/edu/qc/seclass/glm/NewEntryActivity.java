package edu.qc.seclass.glm;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

import java.util.ArrayList;
import java.util.Set;

public class NewEntryActivity extends AppCompatActivity {

    private EditText editTextName, editTextType;
    private Spinner spinnerType;
    private Set<String> typesSet;

    private Button btnBack, btnConfirm;
         
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_database_entry);

        // Initialize views
        editTextName = findViewById(R.id.et_item_name);
        editTextType = findViewById(R.id.et_new_type);
        btnBack = findViewById(R.id.btn_back);
        btnConfirm = findViewById(R.id.btn_confirm);
        spinnerType = findViewById(R.id.spinner_item_type);

        //set click listener to confirm adding
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get name and type
                String itemName = editTextName.getText().toString().trim(),
                    typeOther = editTextType.getText().toString().trim(),
                    typeSelected = spinnerType.getSelectedItem().toString().trim(),
                    type = typeOther;
                if (typeOther.isEmpty())
                    type = typeSelected;

                //try add entry
                if (itemName.isEmpty()) {
                    editTextName.setError("Item name cannot be empty");
                    return;
                }
                if (type.isEmpty()) {
                    Toast.makeText(NewEntryActivity.this, "Type not selected nor given",
                        Toast.LENGTH_SHORT).show();
                    return;
                }
                GroceryDatabase db = GroceryDatabase.getInstance();
                if (db.hasNameType(itemName, type)) {
                    AlertDialog.Builder alertHelp = new AlertDialog.Builder(NewEntryActivity.this);
                    alertHelp.setTitle("Oops!");
                    alertHelp.setMessage("There appears to already be an item entry of "
                        + "the same name and type!");
                    alertHelp.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // close dialog
                            dialog.dismiss();
                        }
                    });
                    alertHelp.show();
                }
                else {
                    db.putItem(itemName, type);
                    // save file
                    db.saveDatabase();
                    finish();
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

    //called automatically when user returns to this activity
    @Override
    protected void onResume() {
        super.onResume();
        refreshTypeDropList();
    }

    private void refreshTypeDropList() {
        // get types from the database
        getTypesInDatabase();
        // Set up the ListView
        ArrayAdapter<String> typesAdapter = new ArrayAdapter<String>(
            NewEntryActivity.this, android.R.layout.simple_spinner_item, new ArrayList<>(typesSet));
        spinnerType.setAdapter(typesAdapter);
    }

    /**
     * get types from the database
     */
    private void getTypesInDatabase() {
        typesSet = GroceryDatabase.getInstance().getTypes();
    }
}