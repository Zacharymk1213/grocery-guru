package edu.qc.seclass.glm;

import android.os.Bundle;
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

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //first, load from local drive and previous data into program
        //use relative path
        //user data and database should be on different save_file
        loadAllData();

        //main activity buttons
        Button openListPage = (Button)findViewById(R.id.button4);
        openListPage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.mock_list);
            }
        });
        Button createListPage = (Button)findViewById(R.id.button5);
        createListPage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.create_list);
            }
        });
        Button searchName = (Button)findViewById(R.id.button3);
        searchName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.search_by_name);
            }
        });
        Button searchType = (Button)findViewById(R.id.button6);
        searchType.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.search_by_type);
            }
        });

        //mock_list buttons
    }

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