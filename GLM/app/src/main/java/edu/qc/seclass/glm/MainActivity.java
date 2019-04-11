package edu.qc.seclass.glm;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sean Rodriguez <sean.rodriguez@outlook.com>
 * Mark Abramov <markabramov01@gmail.com>
 *
 * Main Activity class where the user will be able to create new list, rename lists and delete lists
 * through the use of Dialog boxes. User should be able to enter the AddItemActivity from
 * New List Dialog box, or enter the ListActivity from clicking on a list.
 *
 * @version 1.0
 * @since 1.0
 */

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvLists;
    private List<GroceryList> lists;
    private DatabaseHelper dbHelper;
    GroceryListsAdapter adapter;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvLists = findViewById(R.id.rvLists);
        lists = new ArrayList<>();
        dbHelper = new DatabaseHelper(MainActivity.this);

        // Refactoring needed
        List<Long> listIds = dbHelper.getAllListIDs();
        for (int i = 0; i < listIds.size(); i++) {
            System.out.println(i);
            lists.add(new GroceryList(
                    listIds.get(i),
                    dbHelper.getListNameByID(listIds.get(i))));
        }

        adapter = new GroceryListsAdapter(lists);
        rvLists.setAdapter(adapter);
        rvLists.setLayoutManager(new LinearLayoutManager(this));

        rvLists.addOnItemTouchListener(
                new RecyclerItemClickListener(this, rvLists ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        GroceryList selectedList = lists.get(position);
                        selectedList.setSelected(!selectedList.isSelected());
                        adapter.notifyItemChanged(position);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }


    /**
     * Creates a dialog box to create a new list
     *
     * @return the dialog box that was created
     */
    private Dialog createListDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.create_list_title);

        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogLayout = inflater.inflate(R.layout.dialog_create_list, null);
        builder.setView(dialogLayout);

        builder.setPositiveButton(R.string.confirm_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper = new DatabaseHelper(MainActivity.this);
                EditText input = dialogLayout.findViewById(R.id.new_list_name);
                String listName = input.getText().toString();
                long id = dbHelper.createNewList(listName);
                lists.add(new GroceryList(id, listName));
                adapter.notifyDataSetChanged();
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
     *
     * @param listID The ID of the list in the database being renamed
     * @return The dialog box that was created
     */
    private Dialog renameListDialog(int listID) {
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
     *
     * @param listIDs The IDs of the lists in the database being deleted
     * @return The dialog box that was created
     */
    private Dialog deleteDialog(ArrayList<Integer> listIDs) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add:
                createListDialog().show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
