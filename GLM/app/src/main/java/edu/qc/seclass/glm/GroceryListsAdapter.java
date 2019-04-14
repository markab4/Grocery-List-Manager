package edu.qc.seclass.glm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @author Mark Abramov <markabramov01@gmail.com>
 * @version 1.0
 * @since 1.0
 */

public class GroceryListsAdapter extends android.support.v7.widget.RecyclerView.Adapter<GroceryListsAdapter.ViewHolder> {

    private final ClickListener listener;
    private final List<GroceryList> mGroceryLists;

    public GroceryListsAdapter(List<GroceryList> groceryLists, ClickListener clickListener) {
        mGroceryLists = groceryLists;
        listener = clickListener;
    }

    @NonNull
    @Override
    public GroceryListsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View groceryListView = inflater.inflate(R.layout.grocery_list_display, parent, false);
        // Return a new holder instance
        return new ViewHolder(groceryListView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryListsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        GroceryList groceryList = mGroceryLists.get(position);

        // Set item views based on views and data model
        TextView textView = viewHolder.tvGroceryListName;
        textView.setText(groceryList.getListName());
        CheckBox checkBox = viewHolder.cbIsSelected;
        checkBox.setChecked(groceryList.isSelected());
    }

    @Override
    public int getItemCount() {
        return mGroceryLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView tvGroceryListName;
        public CheckBox cbIsSelected;
        private WeakReference<ClickListener> listenerRef;


        public ViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);

            listenerRef = new WeakReference<>(listener);
            tvGroceryListName = itemView.findViewById(R.id.tvGroceryListName);
            cbIsSelected = itemView.findViewById(R.id.cbSelected);

            itemView.setOnClickListener(this);
            tvGroceryListName.setOnClickListener(this);
            cbIsSelected.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            GroceryList selectedList = mGroceryLists.get(getAdapterPosition());
            if (v.getId() == cbIsSelected.getId()) {
                Toast.makeText(v.getContext(), "Checked!", Toast.LENGTH_SHORT).show();
                selectedList.setSelected(!selectedList.isSelected());
                notifyDataSetChanged();
            } else if (v.getId() == tvGroceryListName.getId()) {
                listenerRef.get().switchActivities(getAdapterPosition());
            }
            listenerRef.get().onPositionClicked(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            listenerRef.get().onLongClicked(getAdapterPosition());
            return true;
        }
    }
}
