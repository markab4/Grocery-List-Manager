package edu.qc.seclass.glm;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * @author      Sean Rodriguez <sean.rodriguez@outlook.com>
 * @version     1.0
 * @since       1.0
 */

public class ListActivity extends AppCompatActivity {

    private int ID;

    private ListView listView;
    private Button addButton;
    private Button deleteButton;
    private Button menuButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Link XML Views to Java
        // listView = findViewById(R.id.listView);
        // addButton = findViewById(R.id.addButton);
        // deleteButton = findViewById(R.id.deleteButton);
        // menuButton = findViewById(R.id.menuButton);

        // TODO: Fetch list items from database
        // TODO: Populate listView with each item
            // Item:
                // Checkbox:
                    // Onclick, toggle boolean, save into database
                // Item:
                    // Onclick, toggle boolean, save into database
                    // OnHold, enter delete mode
                // Quantity:
                    // Onclick, open Quantity Dialog
                        // Confirm - Update database
                        // Cancel

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to addItem Activity
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog(savedInstanceState, 0);
            }
        });
    }

    /**
     * Creates a dialog box to delete the list from the database
     * @param savedInstanceState
     * @param listID The ID of the list being deleted from the database
     * @return The dialog box that was created
     */
    private Dialog deleteDialog(Bundle savedInstanceState, int listID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.delete_list_title);
        builder.setMessage(R.string.delete_list_message);

        builder.setPositiveButton(R.string.confirm_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: Delete list from database, go to MainActivity
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
                // TODO: Change quantity in the database, return to ListActivity
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
