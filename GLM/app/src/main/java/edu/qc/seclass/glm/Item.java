package edu.qc.seclass.glm;

/**
 * @author      Mark Abramov <markabramov01@gmail.com>
 *              Sean Rodriguez <sean.rodriguez@outlook.com>
 *
 * Class contains the id and name of the item, and the item type of the item
 *
 * @version     1.0
 * @since       1.0
 */

public class Item {
    private long ID;
    private String name;
    private ItemType type;

    public Item(long ID, String name, ItemType type) {
        this.ID = ID;
        this.name = name;
        this.type = type;
    }

    public Item(long ID, String name, long typeID, String typeName) {
        this.ID = ID;
        this.name = name;
        this.type = new ItemType(typeID, typeName);
    }

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }
}
