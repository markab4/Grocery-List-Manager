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

import java.util.ArrayList;

/**
 * @author      Sean Rodriguez <sean.rodriguez@outlook.com>
 * @version     1.0
 * @since       1.0
 */

public class MainActivity extends AppCompatActivity {

    private Button addButton;
    private Button deleteButton;
    private Button editButton;
    private ListView listView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link XML Views to Java
        // addButton = findViewById(R.id.addButton);
        // listView = findViewById(R.id.listView);
        // deleteButton = findViewById(R.id.deleteButton);
        // editButton = findViewById(R.id.editButton);

        // Set OnClickListeners for all action bar buttons
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renameListDialog(savedInstanceState, 0);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog(savedInstanceState, null);
            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createListDialog(savedInstanceState);
            }
        });

        // TODO: Fetch list names from database
        // TODO: For each list, append View to ListView
    }

    /**
     * Creates a dialog box to create a new list
     * @param savedInstanceState
     * @return the dialog box that was created
     */
    private Dialog createListDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.create_list_title);

        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_create_list, null));

        builder.setPositiveButton(R.string.confirm_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: Save input into database
            }
        });

        builder.setNegativeButton(R.string.cancel_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: Return to MainActivity
            }
        });

        builder.setNeutralButton(R.string.add_item_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: Save input into database, go to AddItemActivity
            }
        });

        return builder.create();
    }

    /**
     * Creates a dialog box to rename the given list
     * @param savedInstanceState
     * @param listID The ID of the list in the database being renamed
     * @return The dialog box that was created
     */
    private Dialog renameListDialog(Bundle savedInstanceState, int listID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.rename_list_title);

        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_create_list, null));

        builder.setPositiveButton(R.string.confirm_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: Save input into database
            }
        });

        builder.setNegativeButton(R.string.confirm_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: Return to MainActivity
            }
        });

        return builder.create();
    }

    /**
     * Creates a dialog box to delete the selected lists from the database
     * @param savedInstanceState
     * @param listIDs The IDs of the lists in the database being deleted
     * @return The dialog box that was created
     */
    private Dialog deleteDialog(Bundle savedInstanceState, ArrayList<Integer> listIDs) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.delete_lists_title);
        builder.setMessage(R.string.delete_lists_message);

        builder.setPositiveButton(R.string.confirm_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: Delete list from database
            }
        });

        builder.setNegativeButton(R.string.cancel_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: Return to MainActivity
            }
        });

        return builder.create();
    }
}
