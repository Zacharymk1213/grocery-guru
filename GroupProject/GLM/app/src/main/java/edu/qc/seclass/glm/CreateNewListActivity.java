package edu.qc.seclass.glm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateNewListActivity extends AppCompatActivity {

    private EditText editTextListName;
    private Button btnSaveList;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_list);

        // Initialize views
        editTextListName = findViewById(R.id.edit_text_list_name);
        btnSaveList = findViewById(R.id.btn_create_list);
        btnBack = findViewById(R.id.btn_back);

        // Set click listener for the save button
        btnSaveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered list name
                String listName = editTextListName.getText().toString().trim();
                if (!listName.isEmpty()) {
                    // Create a new grocery list with the stored context and pass it back to MainActivity
                    User user = User.getInstance();
                    GroceryList newGroceryList = user.createList(listName);
                    user.saveUserData(getApplicationContext());

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("newGroceryList", (Parcelable) newGroceryList);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    // Show an error message if list name is empty
                    editTextListName.setError("List name cannot be empty");
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
