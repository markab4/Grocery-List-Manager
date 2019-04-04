package edu.qc.seclass.glm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Button addButton;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link XML to Java variables
        // addButton = (Button) findViewById(R.id.addButton);
        // listView = (listView) findViewById(R.id.listView);

        // Fetch list names from database

        // For each list, append View to ListView
            // Display list name
                // On Click, open ListActivity
                // On Hold, activate selection mode
                    // Edit Action (removed from ActionBar if more than one list is selected)
                        // Opens renameList dialog (function)
                            // Input for new list name
                            // Accept Button - Updates database
                            // Cancel Button
                    // Delete Action
                        // Opens "Are You Sure" dialog (function)
                            // Text warning
                            // Accept Button
                            // Cancel Button

        // Add onClickListener for addButton
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Open createList dialog (function)
                    // Input for list name
                    // Cancel Button
                    // Accept Button
                        // Updates database
                    // Add items to list Button
                        // Updates database
                        // Goes to AddItemActivity
            }
        });

    }
}
