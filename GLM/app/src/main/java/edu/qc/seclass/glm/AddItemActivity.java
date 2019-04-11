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
 *
 * Add Item Activity class allows users to navigate through item types and see the items under a
 * specific item type. Users can add items to the List that they entered from and select a quantity
 * and unit type from a dialog box. Users can search for a specific item using the search box. Users
 * can create a new item using the add button on the action menu, and be prompted with a 'New Item'
 * Dialog box.
 *
 * IMPORTANT: An ID of a list should be supplied when entering this activity.
 *
 * @version     1.0
 * @since       1.0
 */

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
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
     * @param savedInstanceState
     * @param itemID The ID of the item that the quantity is being changed
     * @return The dialog box that was created
     */
    private Dialog newItemDialog(Bundle savedInstanceState, int itemID) {
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
