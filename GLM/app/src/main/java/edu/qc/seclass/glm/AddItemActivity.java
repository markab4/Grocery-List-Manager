package edu.qc.seclass.glm;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

/**
 * @author      Sean Rodriguez <sean.rodriguez@outlook.com>
 * @version     1.0
 * @since       1.0
 */

public class AddItemActivity extends AppCompatActivity {

    private int ID;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // Link XML views to Java
        // listView = findViewById(R.id.listView);

        // Search bar
            // Onchange?
                // Search database for item (simple linear search with all lowercase filtering)

        // Get categories from database
        // Populate listView
            // Category
                // OnClick
                    // Get items from category
                    // Populate List View
                    // Add plus button at the end
                        // Opens quantity dialog
    }

    /**
     * Creates a dialog box to select the quantity
     * @param savedInstanceState
     * @param itemID The ID of the item that the quantity is being changed
     * @return The dialog box that was created
     */
    private Dialog quantityDialog(Bundle savedInstanceState, int itemID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.quantity_title);

        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_quantity, null));

        builder.setPositiveButton(R.string.confirm_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: Change quantity in the database, return to AddItemActivity
                // TODO: Display Snackbar to show success of item being added to the correct list
            }
        });

        builder.setNegativeButton(R.string.cancel_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: Return to ListActivity
            }
        });

        return builder.create();
    }


}
