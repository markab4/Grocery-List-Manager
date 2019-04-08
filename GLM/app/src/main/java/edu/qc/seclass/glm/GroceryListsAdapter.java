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

public class GroceryListsAdapter extends android.support.v7.widget.RecyclerView.Adapter<GroceryListsAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvGroceryListName;
        public CheckBox cbIsSelected;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGroceryListName = itemView.findViewById(R.id.tvGroceryListName);
            cbIsSelected = itemView.findViewById(R.id.cbSelected);
        }
    }

    private List<GroceryList> mGroceryLists;

    public GroceryListsAdapter(List<GroceryList> groceryLists){
        mGroceryLists = groceryLists;
    }

    @NonNull
    @Override
    public GroceryListsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.grocery_list_dispay, parent, false);
        // Return a new holder instance
        return new ViewHolder(contactView);
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
}
