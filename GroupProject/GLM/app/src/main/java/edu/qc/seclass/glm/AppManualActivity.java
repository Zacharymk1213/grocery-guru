package edu.qc.seclass.glm;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.ArrayList;

import android.content.res.AssetManager;

import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.TextView;
import android.widget.ScrollView;

public class AppManualActivity extends AppCompatActivity {

    private String manualString;

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

        if (readManualText() == 0)
            tvManual.setText(manualString);

        // Set click listener for the back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the activity and go back to the previous screen
                finish();
            }
        });
    }

    /**
     * Reads the file, simple_app_manual.txt, and loads it into manualString
     * @return 0 if successfully
     */
    private int readManualText() {
        try {
            AssetManager am = getApplicationContext().getAssets();
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(am.open("simple_app_manual.txt"))
            );
            StringBuilder manualText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
                manualText.append(line + "\n");
            manualString = manualText.toString();

            // Close the streams
            reader.close();

            return 0; // Success
        } catch (IOException e) {
            // Handle IO exception
            Log.e("readManualText", "Error reading manual text file: " + e.getMessage());
        }
        return -1; // Error
    }
}
