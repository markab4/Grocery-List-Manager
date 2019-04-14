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
import java.util.Arrays;
import java.util.List;

/**
 * @author Sean Rodriguez <sean.rodriguez@outlook.com>
 * <p>
 * Add Item Activity class allows users to navigate through item types and see the items under a
 * specific item type. Users can add items to the List that they entered from and select a quantity
 * and unit type from a dialog box. Users can search for a specific item using the search box. Users
 * can create a new item using the add button on the action menu, and be prompted with a 'New Item'
 * Dialog box.
 * <p>
 * IMPORTANT: An ID of a list should be supplied when entering this activity.
 * @version 1.0
 * @since 1.0
 */

public class AddItemActivity extends AppCompatActivity {

    List<ItemType> itemTypes;
    ItemTypeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        // Lookup the recyclerview in activity layout
        RecyclerView rvItemTypes = (RecyclerView) findViewById(R.id.rvItemTypes);

        // Initialize item types
        itemTypes = new ArrayList<>(Arrays.asList(
                new ItemType((long) 1234, "Fruit"),
                new ItemType((long) 1738, "Vegetables"),
                new ItemType((long) 6969, "Personal Care")));
        adapter = new ItemTypeAdapter(itemTypes, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {

            }

            @Override
            public void onLongClicked(int position) {

            }

            @Override
            public void switchActivities(int position) {

            }
        });
        rvItemTypes.setAdapter(adapter);
        rvItemTypes.setLayoutManager(new LinearLayoutManager(this));

//        rvItemTypes.addOnItemTouchListener(
//                new RecyclerItemClickListener(this, rvItemTypes, new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        // do whatever you want to do on item click
////                        Intent listIntent = new Intent(MainActivity.this, ListActivity.class);
////                        listIntent.setAction(Intent.ACTION_SEND);
////                        listIntent.putExtra(Intent.EXTRA_TEXT, lists.get(position).getListID());
////                        startActivity(listIntent);
//                    }
//an
//                    @Override
//                    public void onLongItemClick(View view, int position) {
//                        // do whatever you want to do on long item click
////                        GroceryList selectedList = lists.get(position);
////                        selectedList.setSelected(!selectedList.isSelected());
////                        adapter.notifyItemChanged(position);
//                    }
//                })
//        );
    }

    /**
     * Creates a dialog box to select the quantity
     *
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
                // TODO: Add item and quantity to the database
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
     * @param itemID The ID of the item that the quantity is being changed
     * @return The dialog box that was created
     */
    private Dialog newItemDialog(int itemID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle(R.string.new_item_title);

        LayoutInflater inflater = this.getLayoutInflater();
        //builder.setView(inflater.inflate(R.layout.dialog_new_item, null));

        builder.setPositiveButton(R.string.confirm_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: Create new item, refresh list
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
