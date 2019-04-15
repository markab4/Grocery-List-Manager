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

import java.util.ArrayList;
import java.util.Arrays;
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
 * IMPORTANT: An ID of a list should be supplied when entering this activity.
 * @version 1.0
 * @since 1.0
 */

public class ListActivity extends AppCompatActivity {
    private RecyclerView rvItemList;
    private List<GroceryItem> items; //
    private DatabaseHelper dbHelper;
    ItemAdapter adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        rvItemList = findViewById(R.id.rvItemList);
//        // Could need refactoring
//        List<Long> listIds = dbHelper.getAllListIDs();
//        for (int i = 0; i < listIds.size(); i++) {
//            System.out.println(i);
//            lists.add(new GroceryList(
//                    listIds.get(i),
//                    dbHelper.getListNameByID(listIds.get(i))));
//        }
        items = new ArrayList<>(Arrays.asList(
                new GroceryItem(new Item(1, "Coco Puffs", 2, "Cereal"), 2, "boxes"),
                new GroceryItem(new Item(2, "Eggs", 1, "Meat"), 2, "cartons"),
                new GroceryItem(new Item(3, "Bananas", 4, "Fruits"), 2, "units")
        ));

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

//        rvItemList.addOnItemTouchListener(
//                new RecyclerItemClickListener(this, rvItemList, new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        // do whatever you want to do on item click
////                        Intent listIntent = new Intent(MainActivity.this, ListActivity.class);
////                        listIntent.setAction(Intent.ACTION_SEND);
////                        listIntent.putExtra(Intent.EXTRA_TEXT, lists.get(position).getListID());
////                        startActivity(listIntent);
//                        boolean checked = ((CheckBox) view).isChecked();
//                    }
//
//                    @Override
//                    public void onLongItemClick(View view, int position) {
//                        // do whatever you want to do on long item click
////                        GroceryList selectedList = lists.get(position);
////                        selectedList.setSelected(!selectedList.isSelected());
////                        adapter.notifyItemChanged(position);
//
//                    }
//                })
//        );
    }

    /**
     * Creates a dialog box to delete the list from the database
     *
     * @param savedInstanceState
     * @param listID             The ID of the list being deleted from the database
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
                dialog.dismiss();
            }
        });

        return builder.create();
    }

    /**
     * Creates a dialog box to select the quantity
     *
     * @param savedInstanceState
     * @param itemID             The ID of the item that the quantity is being changed
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
                addItemIntent.putExtra(Intent.EXTRA_TEXT, 0);
                startActivity(addItemIntent);
                return true;
            //Sets all checks to false
            case R.id.clearChecks:
                for (int i = 0; i < items.size(); i++) {
                    GroceryItem selectedItem = items.get(i);
                    selectedItem.setChecked(false);
                    adapter.notifyItemChanged(i);
                }
            case R.id.action_delete:
                ArrayList<GroceryItem> selectedItems = new ArrayList<>();
                for (int i=0; i<items.size(); i++) {
                    if (items.get(i).isChecked()) {
                        selectedItems.add(items.get(i));
                    }
                }
                for (int i=0; i<selectedItems.size(); i++) {
                    if (items.contains(selectedItems.get(i))) {
                        Log.d("LIST ACTIVITY", "contains " + selectedItems.get(i).toString());
                        items.remove(selectedItems.get(i));
                    }
                }
                adapter.notifyDataSetChanged();
//                ArrayList<Long> selectedItems = new ArrayList();
//                for (int i = 0; i < items.size(); i++) {
//                    if (items.get(i).isChecked())
//                        selectedItems.add((long)i);
////                    this would impact the database:
////                        selectedItems.add(items.get(i).getItem().getID());
//                }
//                if (!selectedItems.isEmpty())
//                    deleteDialog(selectedItems).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    private Dialog deleteDialog(final ArrayList<Long> itemIDs) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(R.string.delete_items_title);
//        builder.setMessage(R.string.delete_items);
//        builder.setPositiveButton(R.string.confirm_message, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                for (int i = 0; i < itemIDs.size(); i++) {
////                    this would impact the database:
////                    dbHelper.deleteListByID(itemIDs.get(i));
////                    for (int j = 0; j < items.size(); j++) {
////                        if (items.get(j).getItem().getID() == itemIDs.get(i)) {
////                            items.remove(j);
////                        }
////                    }
//                }
//                adapter.notifyDataSetChanged();
//                dialog.dismiss();
//            }
//        });
//
//        builder.setNegativeButton(R.string.cancel_message, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        return builder.create();
//
//    }
}
