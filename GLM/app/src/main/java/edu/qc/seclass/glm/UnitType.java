package edu.qc.seclass.glm;

public class UnitType {
    private long ID;
    private String name;

    public UnitType(long ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
}
