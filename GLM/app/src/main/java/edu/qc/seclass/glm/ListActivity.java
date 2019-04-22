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
 * @author Sean Rodriguez <sean.rodriguez@outlook.com>
 * Mark Abramov <markabramov01@gmail.com>
 * <p>
 * List Activity class users are able to view the items in their list. Users can checkoff a list,
 * update the quantity of a item, add/delete items, group by category, and clear all checkboxes.
 * Users can move back to MainActivity by using the back button on the action menu. Users can go to
 * the AddItemActivity when clicking the add items button on the action menu.
 * <p>
 * @version 1.0
 * @since 1.0
 */

public class ListActivity extends AppCompatActivity {
    private RecyclerView rvItemList;
    private List<GroceryItem> items;
    private DatabaseHelper dbHelper;
    private long listID;
    ItemAdapter adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        rvItemList = findViewById(R.id.rvItemList);
        dbHelper = new DatabaseHelper(this);
        listID = getIntent().getLongExtra("id", -1);

        setTitle(dbHelper.getListNameByID(listID));

        items = dbHelper.getGroceryItemsByListID(listID, false);
        adapter = new ItemAdapter(items, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                Log.d("LIST ACTIVITY", "on position clicked " + position);
            }

            @Override
            public void onLongClicked(int position) {
                Log.d("LIST ACTIVITY", "on long clicked " + position);
            }

            @Override
            public void switchActivities(int position) {

            }
        });
        rvItemList.setAdapter(adapter);
        rvItemList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        items.clear();
        items.addAll(dbHelper.getGroceryItemsByListID(listID, false));
        adapter.notifyDataSetChanged();
    }




    /**
     * Creates a dialog box to rename the given list
     * @param listID The ID of the list in the database being renamed
     * @return The dialog box that was created
     */
    @SuppressWarnings("InflateParams")
    private Dialog renameListDialog(final long listID) {
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
                ListActivity.this.setTitle(input.getText().toString());
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

    //cbItemSelected
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_secondary_inlist_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent addItemIntent = new Intent(ListActivity.this, AddItemActivity.class);
                addItemIntent.putExtra("id", listID);
                startActivity(addItemIntent);
                return true;
            //Sets all checks to false
            case R.id.clearChecks:
                for (int i = 0; i < items.size(); i++) {
                    GroceryItem selectedItem = items.get(i);
                    selectedItem.setChecked(false);
                    dbHelper.clearAllCheckboxesFromList(listID);
                    adapter.notifyItemChanged(i);
                }
                return true;
            case R.id.groupByType:
                items.clear();
                items.addAll(dbHelper.getGroceryItemsByListID(listID, true));
                adapter.notifyDataSetChanged();
                return true;
            case R.id.renameList:
                renameListDialog(listID).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
