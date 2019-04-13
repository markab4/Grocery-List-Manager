package edu.qc.seclass.glm;

/**
 * @author          Mark Abramov <markabramov01@gmail.com>
 *                  Sean Rodriguez <sean.rodriguez@outlook.com>
 *
 * Class contains item, quantity, unit type and a flag if the item is checked off
 *
 * @version     1.0
 * @since       1.0
 */

public class GroceryItem {
    private int quantity;
    private String unitType;
    private boolean isChecked;
    private Item item;

    public GroceryItem(Item item, int quantity, String unitType) {
        this.item = item;
        this.quantity = quantity;
        this.unitType = unitType;
        this.isChecked = false;
    }

    public Item getItem() {
        return item;
    }


    public int getQuantity() {
        return quantity;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
