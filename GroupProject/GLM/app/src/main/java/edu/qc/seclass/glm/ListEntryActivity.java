package edu.qc.seclass.glm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ListEntryActivity extends AppCompatActivity {
    private GroceryItem thisItem;
    private Button btnBack, btnSave, btnDelete;
    private TextView tvName, tvType;
    private EditText editTextQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_entry);
        //get passed data
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            thisItem = extras.getParcelable("groceryItem"); //key must match what was put in other activity

        // Initialize the UI components
        btnBack = findViewById(R.id.btn_back);
        btnSave = findViewById(R.id.btn_save);
        btnDelete = findViewById(R.id.btn_delete);
        tvName = findViewById(R.id.tv_name);
        tvType = findViewById(R.id.tv_type);
        editTextQuantity = findViewById(R.id.editText_quantity);

        setupListeners();

        //set item textViews
        tvName.setText(thisItem.getName());
        tvType.setText(thisItem.getType().toLowerCase());
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());

        btnDelete.setOnClickListener(v -> {
            deleteItem();
            finish();
        });

        btnSave.setOnClickListener(v -> {
            saveItem();
            finish();
        });
    }

    private void saveItem() {
        int quantity = Integer.parseInt(editTextQuantity.getText().toString().trim());
        thisItem.setQuantity(quantity);
    }

    private void deleteItem() {
        // TODO
    }
}
