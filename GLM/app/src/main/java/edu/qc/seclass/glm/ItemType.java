package edu.qc.seclass.glm;

public class ItemType {
    private String typeName;
    private String quantityType;

    public ItemType(String typeName, String quantityType) {
        this.typeName = typeName;
        this.quantityType = quantityType;
    }

    public ItemType(String typeName) {
        this(typeName, "");
    }

    public ItemType() {
        this("Default", "");
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }
}
