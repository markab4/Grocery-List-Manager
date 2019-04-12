package edu.qc.seclass.glm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    // Store a member variable for the grocery items
    private List<GroceryItem> mGroceryItems;

    // Pass in the grocery item array into the constructor
    public ItemAdapter(List<GroceryItem> groceryItems) {
        mGroceryItems = groceryItems;
    }

    // inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.item_row, parent, false);

        // Return a new holder instance
        return new ViewHolder(itemView);
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
        String quantity = String.format("%d %s", item.getQuantity(), item.getUnitType());
        tvQuantity.setText(quantity);
    }

    @Override
    public int getItemCount() {
        return mGroceryItems.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView tvItemName;
        public CheckBox cbSelected;
        public TextView tvQuantity;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            tvItemName = (TextView) itemView.findViewById(R.id.tvItemName);
            cbSelected = (CheckBox) itemView.findViewById(R.id.cbItemSelected);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
        }
    }
}