package edu.qc.seclass.glm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;

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

    // Pass in the grocery item array into the constructor
    public ItemAdapter(List<GroceryItem> groceryItems, ClickListener clickListener) {
        mGroceryItems = groceryItems;
        listener = clickListener;
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
        String quantity = String.format("%d %s", item.getQuantity(), item.getUnitType());
        tvQuantity.setText(quantity);
        ImageView ivIncreaseQuantity = viewHolder.ivIncreaseQuantity,
                ivDecreaseQuantity = viewHolder.ivDecreaseQuantity;
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
        private ImageView ivIncreaseQuantity, ivDecreaseQuantity;
        private WeakReference<ClickListener> listenerRef;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(final View itemView, ClickListener listener) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            listenerRef = new WeakReference<>(listener);
            tvItemName = (TextView) itemView.findViewById(R.id.tvItemName);
            cbSelected = (CheckBox) itemView.findViewById(R.id.cbItemSelected);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            ivIncreaseQuantity = itemView.findViewById(R.id.ivIncreaseQuantity);
            ivDecreaseQuantity = itemView.findViewById(R.id.ivDecreaseQuantity);

            itemView.setOnClickListener(this);
            ivIncreaseQuantity.setOnClickListener(this);
            ivDecreaseQuantity.setOnClickListener(this);
            cbSelected.setOnClickListener(this);

        }

        // onClick Listener for view
        @Override
        public void onClick(View v) {
            GroceryItem selectedItem = mGroceryItems.get(getAdapterPosition());
            if (v.getId() == ivDecreaseQuantity.getId() && selectedItem.getQuantity() > 0) {
                Toast.makeText(v.getContext(), "Decrease Quantity", Toast.LENGTH_SHORT).show();
                selectedItem.setQuantity(selectedItem.getQuantity() - 1);
            } else if (v.getId() == ivIncreaseQuantity.getId()) {
                Toast.makeText(v.getContext(), "Increase Quantity", Toast.LENGTH_SHORT).show();
                selectedItem.setQuantity(selectedItem.getQuantity() + 1);
            } else if (v.getId() == cbSelected.getId()){
                Toast.makeText(v.getContext(), "Checked!", Toast.LENGTH_SHORT).show();
                selectedItem.setChecked(!selectedItem.isChecked());
            }
            notifyDataSetChanged();
            listenerRef.get().onPositionClicked(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}