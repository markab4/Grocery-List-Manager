package edu.qc.seclass.glm;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

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
                new ItemType((long) 1234, "Beverages"),
                new ItemType((long) 1738, "Bread/Bakery"),
                new ItemType((long) 6969, "Canned/Jarred Goods"),
                new ItemType((long) 696969, "Dairy"),
                new ItemType((long) 12345, "Dry/Baking Goods"),
                new ItemType((long) 19, "Frozen Foods"),
                new ItemType((long) 1492, "Meat"),
                new ItemType((long) 14, "Produce"),
                new ItemType((long) 1997, "Cleaners"),
                new ItemType((long) 56788, "Paper Goods"),
                new ItemType((long) 8008, "Personal Care"),
                new ItemType((long) 80085, "Other")
        ));
        adapter = new ItemTypeAdapter(itemTypes, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {

            }

            @Override
            public void onLongClicked(int position) {

            }

            @Override
            public void switchActivities(int position) {
                selectItemDialog(itemTypes.get(position).getName()).show();
            }
        });
        rvItemTypes.setAdapter(adapter);
        rvItemTypes.setLayoutManager(new LinearLayoutManager(this));
    }

    private Dialog selectItemDialog(final String itemTypeName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Item");

        ArrayList<String> itemsInCategory = getItemsInCategory(itemTypeName);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.addAll(itemsInCategory);

        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String selectedItemIs = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(AddItemActivity.this);
                builderInner.setMessage(selectedItemIs);
                builderInner.setTitle("You added the item");
                builderInner.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent listActivityIntent = new Intent(AddItemActivity.this, ListActivity.class);
                        listActivityIntent.putExtra("item", selectedItemIs);
                        listActivityIntent.putExtra("item type", itemTypeName);
                        startActivity(listActivityIntent);
                    }
                });
                builderInner.show();
            }
        });

        builder.setPositiveButton(R.string.custom_item, new DialogInterface.OnClickListener() {
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

    private ArrayList<String> getItemsInCategory(String itemTypeName) {
        ArrayList<String> itemsInCategory;
        switch (itemTypeName){
            case "Beverages":
                itemsInCategory = new ArrayList<>(Arrays.asList(
                        "Coffee",
                        "Tea",
                        "Juice",
                        "Soda"));
                break;
            case "Bread/Bakery":
                itemsInCategory = new ArrayList<>(Arrays.asList(
                        "Sandwich Loaves",
                        "Dinner Rolls",
                        "Tortillas",
                        "Bagels"));
                break;
            case "Canned/Jarred Goods":
                itemsInCategory = new ArrayList<>(Arrays.asList(
                        "Vegetables",
                        "Spaghetti Sauce",
                        "Ketchup",
                        "Mayonaisse"));
                break;
            case "Dairy":
                itemsInCategory = new ArrayList<>(Arrays.asList(
                        "Cheeses",
                        "Eggs",
                        "Milk",
                        "Butter",
                        "Yogurt"));
                break;
            case "Dry/Baking Goods":
                itemsInCategory = new ArrayList<>(Arrays.asList(
                        "Cereals",
                        "Flour",
                        "Sugar",
                        "Pasta",
                        "Mixes"));
                break;
            case "Frozen Foods":
                itemsInCategory = new ArrayList<>(Arrays.asList(
                        "Waffles",
                        "Vegetables",
                        "Individual Meals",
                        "Ice Cream"));
                break;
            case "Meat":
                itemsInCategory = new ArrayList<>(Arrays.asList(
                        "Lunch Meat",
                        "Poultry",
                        "Beef",
                        "Pork"));
                break;
            case "Produce":
                itemsInCategory = new ArrayList<>(Arrays.asList(
                        "Fruits",
                        "Vegetables"));
                break;
            case "Cleaners":
                itemsInCategory = new ArrayList<>(Arrays.asList(
                        "All-Purpose",
                        "Laundry Detergent",
                        "Dishwashing Liquid",
                        "Dishwashing Detergent"));
                break;
            case "Paper Goods":
                itemsInCategory = new ArrayList<>(Arrays.asList(
                        "Paper Towels",
                        "Toilet Paper",
                        "Aluminum Foil",
                        "Sandwich Bags"));
                break;
            case "Personal Care":
                itemsInCategory = new ArrayList<>(Arrays.asList(
                        "Shampoo",
                        "Soap",
                        "Hand Soap",
                        "Shaving Cream"));
                break;
            default:
                itemsInCategory = new ArrayList<>(Arrays.asList(
                        "Baby Items",
                        "Pet Items",
                        "Batteries",
                        "Greeting Cards"));
                break;
        }
        return itemsInCategory;
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
