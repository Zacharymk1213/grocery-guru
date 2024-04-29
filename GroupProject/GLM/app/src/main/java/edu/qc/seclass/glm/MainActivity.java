package edu.qc.seclass.glm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the buttons by their IDs
        Button btnMyLists = findViewById(R.id.btn_my_lists);
        Button btnCreateNewList = findViewById(R.id.btn_create_new_list);
        Button btnSearchItem = findViewById(R.id.btn_search_item);
        Button btnSearchType = findViewById(R.id.btn_search_type);

        // Set click listeners for the buttons
        btnMyLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to My Lists activity
            //    startActivity(new Intent(MainActivity.this, MyListsActivity.class));
            }
        });

        btnCreateNewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the Create New List activity
                Intent intent = new Intent(MainActivity.this, CreateNewListActivity.class);
                startActivity(intent);
            }
        });



        btnSearchItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Search Item activity
                startActivity(new Intent(MainActivity.this, SearchItemActivity.class));
            }
        });

        btnSearchType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Search Type activity
                startActivity(new Intent(MainActivity.this, SearchByTypeActivity.class));
            }
        });
    }



        //first, load from local drive and previous data into program
        //use relative path
        //user data and database should be on different save_file


    /**
     * load all user data, their grocery lists and database from drive.
     * @return 0 if save is successful
     */
    public int loadAllData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("user_data.json"))) {
            StringBuilder jsonData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }

            // Parse JSON data
            JSONObject jsonObject = new JSONObject(jsonData.toString());

            // Load user data
            JSONObject userDataJson = jsonObject.getJSONObject("userData");
            // Load other data like grocery lists and database

            return 0; // Success
        } catch (IOException e) {
            // Handle IO exception
            Log.e("LoadData", "Error reading JSON file: " + e.getMessage());
        } catch (JSONException e) {
            // Handle JSON exception
            Log.e("LoadData", "Error parsing JSON: " + e.getMessage());
        }
        return -1; // Error
    }

    /**
     * saves all user data, their grocery lists and database to drive.
     * @return 0 if save is successful
     */
        public int saveAllData() {
            try (FileWriter fileWriter = new FileWriter("user_data.json")) {
                // Create JSON object for user data
                JSONObject userDataJson = new JSONObject();
                // Add user data fields to userDataJson
                // For example: userDataJson.put("name", userName);

                // Create JSON object for other data like grocery lists and database

                // Create a main JSON object to hold all data
                JSONObject mainJsonObject = new JSONObject();
                mainJsonObject.put("userData", userDataJson);
                // Add other data objects to mainJsonObject

                // Write JSON data to file
                fileWriter.write(mainJsonObject.toString());
                return 0; // Success
            } catch (IOException e) {
                // Handle IO exception
                Log.e("SaveData", "Error writing JSON file: " + e.getMessage());
            } catch (JSONException e) {
                // Handle JSON exception
                Log.e("SaveData", "Error creating JSON: " + e.getMessage());
            }
            return -1; // Error
        }
}