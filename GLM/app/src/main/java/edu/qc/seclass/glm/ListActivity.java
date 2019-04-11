package edu.qc.seclass.glm;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * @author      Sean Rodriguez <sean.rodriguez@outlook.com>
 *
 * List Activity class users are able to view the items in their list. Users can checkoff a list,
 * update the quantity of a item, add/delete items, group by category, and clear all checkboxes.
 * Users can move back to MainActivity by using the back button on the action menu. Users can go to
 * the AddItemActivity when clicking the add items button on the action menu.
 *
 * IMPORTANT: An ID of a list should be supplied when entering this activity.
 *
 * @version     1.0
 * @since       1.0
 */

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
