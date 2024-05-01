package edu.qc.seclass.glm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewEntryActivity extends AppCompatActivity {

    private EditText editTextName;
    private Button btnBack,
                btnConfirm;
    private Spinner spinnerType;
         
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_entry);

        // Initialize views
        editTextName = findViewById(R.id.et_item_name);
        btnBack = findViewById(R.id.btn_back);
        btnConfirm = findViewById(R.id.btn_confirm);
        spinnerType = findViewById(R.id.spinner_item_type);

        //set click listener to confirm adding
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get name and type
                String itemName = editTextName.getText().toString().trim();
                String type = spinnerType.getSelectedItem().toString().trim();
                //try add entry
                if (itemName.isEmpty())
                    editTextName.setError("Item name cannot be empty");
                else if (type.isEmpty())
                    Toast.makeText(NewEntryActivity.this, "Type not selected", Toast.LENGTH_SHORT).show();
                else {
                    GroceryDatabase.getInstance().putItem(itemName, type);
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
}
