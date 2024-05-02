package edu.qc.seclass.glm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MockListActivity extends AppCompatActivity {
    private Button btnBackMocklist, btnSearchItemMocklist, btnSearchTypeMocklist;
    private TextView tvNameMocklist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mock_list);

        initializeUI();

        setupListeners();
    }

    private void initializeUI() {
        btnBackMocklist = findViewById(R.id.btn_back_mocklist);
        btnSearchItemMocklist = findViewById(R.id.btn_search_item_mocklist);
        btnSearchTypeMocklist = findViewById(R.id.btn_search_type_mocklist);

        tvNameMocklist = findViewById(R.id.tv_name_mocklist);

    }

    private void setupListeners() {
        btnBackMocklist.setOnClickListener(v -> finish());

        btnSearchItemMocklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Search Item activity
                startActivity(new Intent(MockListActivity.this, SearchItemActivity.class));
            }
        });
        btnSearchTypeMocklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Search Type activity
                startActivity(new Intent(MockListActivity.this, SearchByTypeActivity.class));
            }
        });



    }


    }
