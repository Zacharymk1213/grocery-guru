package edu.qc.seclass.glm;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.TextView;
import android.widget.ScrollView;

public class AppManualActivity extends AppCompatActivity {

    //GUI components
    Button btnBack;
    TextView tvManual;
    ScrollView svManualScroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual);

        btnBack = findViewById(R.id.btn_back);
        tvManual = findViewById(R.id.tv_manual_body);
        svManualScroller = findViewById(R.id.sv_manual_scroller);

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
