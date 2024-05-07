package edu.qc.seclass.glm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ListEntryActivity extends AppCompatActivity {

    private GroceryList openedList;
    private GroceryItem thisItem;

    //GUI components
    private Button btnBack, btnSave;
    private TextView tvName, tvType;
    private EditText editTextQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_entry);
        //get passed data
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //keys must match what was put in other activity
            openedList = extras.getParcelable("openedList");
            thisItem = extras.getParcelable("groceryItem");
        }
        // Initialize the UI components
        btnBack = findViewById(R.id.btn_back);
        btnSave = findViewById(R.id.btn_save);
        tvName = findViewById(R.id.tv_name);
        tvType = findViewById(R.id.tv_type);
        editTextQuantity = findViewById(R.id.editText_quantity);

        setupListeners();

        //set item textViews
        tvName.setText(thisItem.getName());
        tvType.setText(thisItem.getType().toLowerCase());
        editTextQuantity.setText(""+thisItem.getQuantity());
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> {
            saveItem();
            finish();
        });
    }

    private void saveItem() {
        int quantity = Integer.parseInt(editTextQuantity.getText().toString().trim());
        //save to the list's exact instance of item
        openedList.getItem(thisItem.getId()).setQuantity(quantity);
    }

    //called automatically when user moved away from this activity
    //(i.e. homepage is not on the screen any more)
    @Override
    protected void onPause() {
        super.onPause();
        //save user data! User only!
        User.getInstance().saveUserData(getApplicationContext());
    }
}
