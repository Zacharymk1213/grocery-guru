package edu.qc.seclass.glm;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
    }

    /**
     * saves all user data, their grocery lists and database to drive.
     * @return 0 if save is successful
     */
    public int loadAllData() {
        
    }

    /**
     * saves all user data, their grocery lists and database to drive.
     * @return 0 if save is successful
     */
    public int saveAllData() {
        
    }
}