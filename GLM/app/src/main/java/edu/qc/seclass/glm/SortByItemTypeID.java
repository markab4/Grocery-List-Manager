package edu.qc.seclass.glm;

import java.util.Comparator;

public class SortByItemTypeID implements Comparator<GroceryItem> {
    @Override
    public int compare(GroceryItem o1, GroceryItem o2) {
        return (int) (o1.getItem().getType().getID() - o2.getItem().getType().getID());
    }
}
