package edu.qc.seclass.glm;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sean Rodriguez <sean.rodriguez@outlook.com>, Mark Abramov <markabramov01@gmail.com>
 * <p>
 * Main Activity class where the user will be able to create new list, rename lists and delete lists
 * through the use of Dialog boxes. User should be able to enter the AddItemActivity from
 * New List Dialog box, or enter the ListActivity from clicking on a list.
 * @version 1.0
 * @since 1.0
 */

public class MainActivity extends AppCompatActivity {

    private List<GroceryList> lists;
    private DatabaseHelper dbHelper;
    GroceryListsAdapter adapter;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvLists = findViewById(R.id.rvItemTypes);
        lists = new ArrayList<>();
        dbHelper = new DatabaseHelper(MainActivity.this);

        // Could need refactoring
        List<Long> listIds = dbHelper.getAllListIDs();
        for (int i = 0; i < listIds.size(); i++) {
            System.out.println(i);
            lists.add(new GroceryList(
                    listIds.get(i),
                    dbHelper.getListNameByID(listIds.get(i))));
        }

        adapter = new GroceryListsAdapter(lists, new ClickListener() {

            @Override
            public void onPositionClicked(int position) {
                Log.d("MAIN ACTIVITY", "on position clicked " + position);
            }

            @Override
            public void onLongClicked(int position) {
                Log.d("MAIN ACTIVITY", "on long clicked " + position);
            }

            @Override
            public void switchActivities(int position) {
                Intent listIntent = new Intent(MainActivity.this, ListActivity.class);
                listIntent.setAction(Intent.ACTION_SEND);
                listIntent.putExtra(Intent.EXTRA_TEXT, lists.get(position).getListID());
                startActivity(listIntent);
            }
        });
        rvLists.setAdapter(adapter);
        rvLists.setLayoutManager(new LinearLayoutManager(this));
    }


    /**
     * Creates a dialog box to create a new list
     *
     * @return the dialog box that was created
     */
    @SuppressWarnings("InflateParams")
    private Dialog createListDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.create_list_title);

        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogLayout = inflater.inflate(R.layout.dialog_create_list, null);
        builder.setView(dialogLayout);

        builder.setPositiveButton(R.string.confirm_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText input = dialogLayout.findViewById(R.id.new_list_name);
                String listName = input.getText().toString();

                if (listName.isEmpty()) listName = "My Grocery List";
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
                EditText input = dialogLayout.findViewById(R.id.new_list_name);
                String listName = input.getText().toString();

                if (listName.isEmpty()) listName = "My Grocery List";

                long id = dbHelper.createNewList(listName);
                lists.add(new GroceryList(id, listName));
                adapter.notifyDataSetChanged();

                Intent addItemIntent = new Intent(MainActivity.this, AddItemActivity.class);
                addItemIntent.putExtra(Intent.EXTRA_TEXT, id);
                startActivity(addItemIntent);
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
    @SuppressWarnings("InflateParams")
    private Dialog renameListDialog(final long listID, final int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.rename_list_title);

        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogLayout = inflater.inflate(R.layout.dialog_create_list, null);
        builder.setView(dialogLayout);

        builder.setPositiveButton(R.string.confirm_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText input = dialogLayout.findViewById(R.id.new_list_name);

                dbHelper.renameListByID(listID, input.getText().toString());
                lists.get(index).setListName(input.getText().toString());

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

        return builder.create();
    }

    /**
     * Creates a dialog box to delete the selected lists from the database
     *
     * @param listIDs The IDs of the lists in the database being deleted
     * @return The dialog box that was created
     */
    private Dialog deleteDialog(final ArrayList<Long> listIDs) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.delete_lists_title);
        builder.setMessage(R.string.delete_lists_message);

        builder.setPositiveButton(R.string.confirm_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < listIDs.size(); i++) {
                    dbHelper.deleteListByID(listIDs.get(i));
                    for (int j = 0; j < lists.size(); j++)
                        if (lists.get(j).getListID() == listIDs.get(i))
                            lists.remove(j);
                }

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
            case R.id.action_edit:
                ArrayList<Long> selectedList = new ArrayList();
                int index = -1;

                for (int i = 0; i < lists.size(); i++) {
                    if (lists.get(i).isSelected()) {
                        selectedList.add(lists.get(i).getListID());
                        if (selectedList.size() == 1) index = i;
                        else break;
                    }
                }

                if (selectedList.size() == 1)
                    renameListDialog(selectedList.get(0), index).show();
                else
                    Toast.makeText(this, "Only one list can be selected to be renamed.",
                            Toast.LENGTH_SHORT).show();

                return true;
            case R.id.action_delete:
                ArrayList<Long> selectedLists = new ArrayList();
                for (int i = 0; i < lists.size(); i++) {
                    if (lists.get(i).isSelected())
                        selectedLists.add(lists.get(i).getListID());
                }
                if (selectedLists.size() >= 1)
                    deleteDialog(selectedLists).show();
                else
                    Toast.makeText(this, "No list are selected to be deleted",
                            Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
