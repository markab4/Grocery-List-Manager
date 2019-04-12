package edu.qc.seclass.glm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author Mark Abramov <markabramov01@gmail.com>
 * @version 1.0
 * @since 1.0
 */

public class ItemTypeAdapter extends RecyclerView.Adapter<ItemTypeAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView tvItemTypeName;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            tvItemTypeName = (TextView) itemView.findViewById(R.id.tvItemTypeName);
        }
    }

    // Store a member variable for the item types
    private List<ItemType> mItemTypes;

    // Pass in the item types array into the constructor
    public ItemTypeAdapter(List<ItemType> itemTypes) {
        mItemTypes = itemTypes;
    }

    @NonNull
    // inflating a layout from XML and returning the holder
    @Override
    public ItemTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemTypeView = inflater.inflate(R.layout.item_type_row, parent, false);

        // Return a new holder instance
        return new ViewHolder(itemTypeView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ItemTypeAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ItemType itemType = mItemTypes.get(position);

        // Set item views based on views and data model
        TextView textView = viewHolder.tvItemTypeName;
        textView.setText(itemType.getName());
    }

    @Override
    public int getItemCount() {
        return mItemTypes.size();
    }
}
