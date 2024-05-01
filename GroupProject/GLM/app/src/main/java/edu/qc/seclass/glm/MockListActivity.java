package edu.qc.seclass.glm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MockListActivity extends AppCompatActivity {
    private Button btnBackMocklist, btnSearchItemMocklist, btnSearchTypeMocklist, btnReturnToHomepageMocklist, btnItemMocklist, btnMinusMocklist, btnPlusMocklist, btnXMocklist, btnCheckMocklist;
    private TextView tvNameMocklist;
    private EditText etQuantityMocklist;

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
        btnReturnToHomepageMocklist = findViewById(R.id.btn_return_to_Homepage_mocklist);
        btnItemMocklist = findViewById(R.id.btn_item_mocklist);
        btnCheckMocklist = findViewById(R.id.btn_check_mocklist);
        tvNameMocklist = findViewById(R.id.tv_name_mocklist);
        etQuantityMocklist = findViewById(R.id.et_quantity_mocklist);
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

        btnReturnToHomepageMocklist.setOnClickListener(v -> {
            // TODO: back to home
        });

        btnMinusMocklist.setOnClickListener(v -> adjustQuantity(-1));

        btnPlusMocklist.setOnClickListener(v -> adjustQuantity(1));

        btnXMocklist.setOnClickListener(v -> {
            // TODO: delete
        });

        btnCheckMocklist.setOnClickListener(v -> {
            // TODO: check
        });
    }

    private void adjustQuantity(int adjustment) {
        try {
            int currentQuantity = Integer.parseInt(etQuantityMocklist.getText().toString());
            currentQuantity += adjustment;
            if (currentQuantity < 0) {
                currentQuantity = 0;
            }
            etQuantityMocklist.setText(String.valueOf(currentQuantity));
        } catch (NumberFormatException e) {
            etQuantityMocklist.setText("0");
        }
    }
}
