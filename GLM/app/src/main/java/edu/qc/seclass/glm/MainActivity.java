package edu.qc.seclass.glm;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * @author      Sean Rodriguez <sean.rodriguez@outlook.com>
 *              Mark Abramov <markabramov01@gmail.com>
 * @version     1.0
 * @since       1.0
 */

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvLists;
    private List<GroceryList> lists;
    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvLists = findViewById(R.id.rvLists);
        lists =  new ArrayList<>();
        dbHelper = new DatabaseHelper(this);

        // Refactoring needed
        List<Long> listIds = dbHelper.getAllListIDs();
        for(int i = 0; i < listIds.size(); i++) {
            lists.add(new GroceryList(dbHelper.getListNameByID(listIds.get(i))));
        }

        GroceryListsAdapter adapter = new GroceryListsAdapter(lists);
        rvLists.setAdapter(adapter);
        rvLists.setLayoutManager(new LinearLayoutManager(this));
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
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(R.string.cancel_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
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
                // TODO: Update database
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(R.string.cancel_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
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
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(R.string.cancel_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }
}
