package edu.qc.seclass.glm;

public class GroceryList {
    private String listName;
    private boolean isSelected;

    public GroceryList(String name){
        listName = name;
        isSelected = false;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
