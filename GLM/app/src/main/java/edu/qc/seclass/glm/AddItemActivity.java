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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sean Rodriguez <sean.rodriguez@outlook.com>, Mark Abramov <markabramov01@gmail.com>
 * <p>
 * Add Item Activity class allows users to navigate through item types and see the items under a
 * specific item type. Users can add items to the List that they entered from and select a quantity
 * and unit type from a dialog box. Users can search for a specific item using the search box. Users
 * can create a new item using the add button on the action menu, and be prompted with a 'New Item'
 * Dialog box.
 * <p>
 * @version 1.0
 * @since 1.0
 */

public class AddItemActivity extends AppCompatActivity {

    List<ItemType> itemTypes;
    ItemTypeAdapter adapter;
    DatabaseHelper dbHelper;
    Button searchButton;
    long listID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        listID = getIntent().getLongExtra("id", -1);

        RecyclerView rvItemTypes = findViewById(R.id.rvItemTypes);
        searchButton = findViewById(R.id.search_button);
        dbHelper = new DatabaseHelper(this);
        itemTypes = dbHelper.getAllItemTypes();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText searchInput = findViewById(R.id.search_input);
                List<Item> items = dbHelper.getSearchResultsByItemName(searchInput.getText().toString());

                if(items.isEmpty())
                    Toast.makeText(AddItemActivity.this, "No results found", Toast.LENGTH_SHORT).show();
                else selectItemDialog(items).show();

            }
        });

        adapter = new ItemTypeAdapter(itemTypes, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {}

            @Override
            public void onLongClicked(int position) {}

            @Override
            public void switchActivities(int position) {
                selectItemDialog(itemTypes.get(position).getID()).show();
            }
        });
        rvItemTypes.setAdapter(adapter);
        rvItemTypes.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Creates a dialog box to select items to add
     * @param id the id of the item type selected
     * @return the dialog box
     */
    private Dialog selectItemDialog(final long id) {
        final DatabaseHelper dbHelper = new DatabaseHelper(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Item");

        List<Item> items = dbHelper.getItemsFromItemTypeID(id);
        ArrayList<String> itemsInCategory = new ArrayList<>();

        for(int i = 0; i < items.size(); i++) {
            itemsInCategory.add(items.get(i).getName());
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.addAll(itemsInCategory);

        // Inline Adapter for Items
        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                quantityDialog(dbHelper.getItemIdByName(arrayAdapter.getItem(which))).show();
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
     * Creates a dialog box displaying the search results of the query
     * @param items the search query for the item
     * @return the dialog box
     */
    private Dialog selectItemDialog(List<Item> items) {
        final DatabaseHelper dbHelper = new DatabaseHelper(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Item");

        ArrayList<String> itemsInCategory = new ArrayList<>();

        for(int i = 0; i < items.size(); i++) {
            itemsInCategory.add(items.get(i).getName());
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.addAll(itemsInCategory);

        // Inline Adapter for Items
        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                quantityDialog(dbHelper.getItemIdByName(arrayAdapter.getItem(which))).show();
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
     * @param itemID The ID of the item that the quantity is being changed
     * @return The dialog box that was created
     */
    private Dialog quantityDialog(final long itemID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.quantity_title);

        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogLayout = inflater.inflate(R.layout.dialog_quantity, null);
        builder.setView(dialogLayout);

        final Spinner unitTypeSpinner = dialogLayout.findViewById(R.id.unit_type_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        List<UnitType> allUnitTypes = dbHelper.getAllUnitTypes();
        final List<String> unitTypes = new ArrayList();
        for(int i = 0; i < allUnitTypes.size(); i++) {
            unitTypes.add(allUnitTypes.get(i).getName());
        }

        adapter.addAll(unitTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitTypeSpinner.setAdapter(adapter);

        builder.setPositiveButton(R.string.confirm_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selectedUnitType = unitTypeSpinner.getSelectedItem().toString();
                EditText editText = dialogLayout.findViewById(R.id.quantity_et);
                if(editText.getText().toString().isEmpty()) {
                    dialog.dismiss();
                    return;
                }

                dbHelper.addItemToList(itemID,
                        Integer.parseInt(editText.getText().toString()),
                        dbHelper.getUnitTypeIDByName(selectedUnitType), listID);

                Toast.makeText(AddItemActivity.this, "Item added to list", Toast.LENGTH_SHORT).show();
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
     * Creates a new item for catalog
     * @return The dialog box that was created
     */
    private Dialog newItemDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.new_item_title);

        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogLayout = inflater.inflate(R.layout.dialog_new_item, null);
        builder.setView(dialogLayout);

        final Spinner itemTypeSpinner = dialogLayout.findViewById(R.id.item_type_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        List<ItemType> allItemTypes = dbHelper.getAllItemTypes();
        final List<String> itemTypes = new ArrayList();
        for(int i = 0; i < allItemTypes.size(); i++) {
            itemTypes.add(allItemTypes.get(i).getName());
        }

        adapter.addAll(itemTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemTypeSpinner.setAdapter(adapter);

        builder.setPositiveButton(R.string.confirm_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText itemInput = dialogLayout.findViewById(R.id.item_et);
                String selectedItemType = itemTypeSpinner.getSelectedItem().toString();

                if(itemInput.getText().toString().isEmpty()) {
                    dialog.dismiss();
                    return;
                }

                dbHelper.createNewItem(itemInput.getText().toString(),
                        dbHelper.getItemTypeIDByName(selectedItemType));
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
        inflater.inflate(R.menu.activity_add_items_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_item:
                newItemDialog().show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
