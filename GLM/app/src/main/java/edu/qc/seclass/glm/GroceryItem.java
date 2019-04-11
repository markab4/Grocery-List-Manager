package edu.qc.seclass.glm;

public class GroceryItem extends ItemType {
    private int quantity;
    private String itemName;
    private boolean isChecked;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public GroceryItem(String itemName, int quantity) {
        super();
        this.quantity = quantity;
        this.itemName = itemName;
        this.isChecked = false;
    }

    public GroceryItem(String itemName){
        this(itemName, 1);
    }
}
