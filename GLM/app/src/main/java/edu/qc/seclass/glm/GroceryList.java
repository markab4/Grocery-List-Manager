package edu.qc.seclass.glm;

/**
 * @author      Mark Abramov <markabramov01@gmail.com>
 *              Sean Rodriguez <sean.rodriguez@outlook.com>
 *
 * @version     1.0
 * @since       1.0
 */

public class GroceryList {
    private String listName;
    private boolean isSelected;
    private long ID;

    public GroceryList(Long ID, String name){
        this.ID = ID;
        this.listName = name;
        this.isSelected = false;
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
