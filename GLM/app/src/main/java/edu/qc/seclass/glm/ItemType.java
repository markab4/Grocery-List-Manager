package edu.qc.seclass.glm;

/**
 * @author Mark Abramov <markabramov01@gmail.com>
 * Sean Rodriguez <sean.rodriguez@outlook.com>
 * <p>
 * Class contains the name of the item type and its corresponding id from the database
 * @version 1.0
 * @since 1.0
 */

public class ItemType {
    private long ID;
    private String name;

    public ItemType(Long ID, String name) {
        this.name = name;
        this.ID = ID;
    }

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

//    public ItemType(String typeName) {
//        this(typeName, "");
//    }

//    public ItemType() {
//        this("Default", "");
//    }


//    public void setTypeName(String typeName) {
//        this.typeName = typeName;
//    }
//
//    public String getQuantityType() {
//        return quantityType;
//    }
//
//    public void setQuantityType(String quantityType) {
//        this.quantityType = quantityType;
//    }
}
