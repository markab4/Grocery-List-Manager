package edu.qc.seclass.glm;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Mark Abramov <markabramov01@gmail.com>
 * @version 1.0
 * @since 1.0
 */

// Create the basic adapter extending from RecyclerView.Adapter
// the custom ViewHolder gives us access to our views
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private final ClickListener listener;
    // Store a member variable for the grocery items
    private List<GroceryItem> mGroceryItems;
    private Context context;

    // Pass in the grocery item array into the constructor
    public ItemAdapter(List<GroceryItem> groceryItems, ClickListener clickListener) {
        mGroceryItems = groceryItems;
        listener = clickListener;
    }

    // inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.item_row, parent, false);

        // Return a new holder instance
        return new ViewHolder(itemView, listener);
    }

    // populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        // Get the data model based on position
        GroceryItem item = mGroceryItems.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.tvItemName;
        textView.setText(item.getItem().getName());
        CheckBox cbSelected = viewHolder.cbSelected;
        cbSelected.setChecked(item.isChecked());
        TextView tvQuantity = viewHolder.tvQuantity;
        String quantity = String.format(Locale.ENGLISH, "%d %s", item.getQuantity(), item.getUnitType());
        tvQuantity.setText(quantity);
    }

    @Override
    public int getItemCount() {
        return mGroceryItems.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        private TextView tvItemName;
        private CheckBox cbSelected;
        private TextView tvQuantity;
        private WeakReference<ClickListener> listenerRef;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(final View itemView, ClickListener listener) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            listenerRef = new WeakReference<>(listener);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            cbSelected = itemView.findViewById(R.id.cbItemSelected);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);

            itemView.setOnClickListener(this);
            cbSelected.setOnClickListener(this);
            tvQuantity.setOnClickListener(this);

            itemView.setOnLongClickListener(this);

        }

        // onClick Listener for view
        @Override
        public void onClick(View v) {
            DatabaseHelper db = new DatabaseHelper(context);
            GroceryItem selectedItem = mGroceryItems.get(getAdapterPosition());

            if  (v.getId() == cbSelected.getId()){
                selectedItem.setChecked(!selectedItem.isChecked());
                db.setCheckedForListItem(selectedItem.getId(), selectedItem.isChecked());
            } else if(v.getId() == tvQuantity.getId()) {
                quantityDialog(selectedItem.getId()).show();
            }

            notifyDataSetChanged();
            listenerRef.get().onPositionClicked(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            GroceryItem selectedItem = mGroceryItems.get(getAdapterPosition());

            if (v.getId() == itemView.getId())
                deleteDialog(selectedItem.getId()).show();

            notifyDataSetChanged();
            listenerRef.get().onPositionClicked(getAdapterPosition());
            return true;
        }

        /**
         * Creates a dialog box to select the quantity
         * @param itemID The ID of the item that the quantity is being changed
         * @return The dialog box that was created
         */
        private Dialog quantityDialog(final long itemID) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(R.string.quantity_title);
            final DatabaseHelper dbHelper = new DatabaseHelper(context);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View dialogLayout = inflater.inflate(R.layout.dialog_quantity, null);
            builder.setView(dialogLayout);

            final Spinner unitTypeSpinner = dialogLayout.findViewById(R.id.unit_type_spinner);
            final ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item);
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

                    dbHelper.updateQuantity(itemID, Integer.parseInt(editText.getText().toString()),
                            dbHelper.getUnitTypeIDByName(selectedUnitType));

                    mGroceryItems.get(getAdapterPosition()).setQuantity(Integer.parseInt(editText.getText().toString()));
                    mGroceryItems.get(getAdapterPosition()).setUnitType(selectedUnitType);
                    notifyDataSetChanged();
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
         * Creates a dialog box to delete the item from the database
         * @param id The ID of the item in the database being deleted
         * @return The dialog box that was created
         */
        private Dialog deleteDialog(final long id) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setTitle(R.string.delete_item_title);
            builder.setMessage(R.string.delete_item_message);

            builder.setPositiveButton(R.string.confirm_message, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DatabaseHelper dbHelper = new DatabaseHelper(context);

                    dbHelper.deleteItemFromList(id);
                    mGroceryItems.remove(getAdapterPosition());

                    notifyDataSetChanged();
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
}