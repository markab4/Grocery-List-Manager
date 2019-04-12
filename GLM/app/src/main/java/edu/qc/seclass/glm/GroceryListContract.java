package edu.qc.seclass.glm;

import android.provider.BaseColumns;

/**
 * @author      Sean Rodriguez <sean.rodriguez@outlook.com>
 *
 * Utility Class used to define the schema of the database. Included are SQL queries to create the
 * database on newly installed applications, and classes of each table in the database. Do not
 * create a column for ID. BaseColumns has a variable, _id, that will auto-increment when a new
 * row is added to the table.
 *
 * @version     1.0
 * @since       1.0
 */

public final class GroceryListContract {

    public static final String[] SQL_CREATE_ENTRIES = {

            // Grocery List
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT" + ");",
                    GroceryList.TABLE_NAME, GroceryList._ID, GroceryList.COLUMN_NAME),

            // Item Type
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT);",
                    ItemType.TABLE_NAME, ItemType._ID, ItemType.COLUMN_NAME),

            // Unit Type
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT);",
                    UnitType.TABLE_NAME, UnitType._ID, UnitType.COLUMN_NAME),

            // Item
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s INTEGER, %s TEXT," +
                                "FOREIGN KEY (%s) REFERENCES %s(%s));",
                    Item.TABLE_NAME, Item._ID, Item.COLUMN_TYPE_ID, Item.COLUMN_NAME,
                    Item.COLUMN_TYPE_ID, ItemType.TABLE_NAME, ItemType._ID),

            //ListItem
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s REAL, %s INTEGER," +
                                "%s INTEGER, %s INTEGER," +
                                "FOREIGN KEY (%s) REFERENCES %s(%s)," +
                                "FOREIGN KEY (%s) REFERENCES %s(%s)," +
                                "FOREIGN KEY (%s) REFERENCES %s(%s)" +
                            ");",
                    ListItem.TABLE_NAME, ListItem._ID, ListItem.COLUMN_QUANTITY,
                    ListItem.COLUMN_UNIT_TYPE_ID, ListItem.COLUMN_ITEM_ID, ListItem.COLUMN_LIST_ID,
                    ListItem.COLUMN_UNIT_TYPE_ID, UnitType.TABLE_NAME, UnitType._ID,
                    ListItem.COLUMN_ITEM_ID, Item.TABLE_NAME, Item._ID,
                    ListItem.COLUMN_LIST_ID, GroceryList.TABLE_NAME, GroceryList._ID)

    };

    public static final String[] DEFAULT_UNIT_TYPES = {
            "Pounds",
            "Ounces",
            "Grams",
            "Milligrams",
            "Liters",
            "Milliliters",
            "Gallons",
            "Quarts",
            "Pints",
            "Teaspoons",
            "Tablespoons",
            "Units",
            "Boxes",
            "Bags"
    };

    public static final String[] DEFAULT_ITEM_TYPES = {
            "Fruits",
            "Vegetables",
            "Grains",
            "Dairy",
            "Meat",
            "Drinks",
            "Seafood"
    };

    public static final String[] DEFAULT_ITEMS = {
            "Apples",
            "Bananas",
            "Pizza",
            "Apple Juice",
            "Milk",
            "Peas",
            "Chicken",
            "Water"
    };

    private GroceryListContract() {}

    public static class ItemType implements BaseColumns {
        public static final String TABLE_NAME = "ItemType";
        public static final String COLUMN_NAME = "Name";
    }

    public static class UnitType implements BaseColumns {
        public static final String TABLE_NAME = "UnitType";
        public static final String COLUMN_NAME = "Name";
    }

    public static class Item implements BaseColumns {
        public static final String TABLE_NAME = "Item";
        public static final String COLUMN_TYPE_ID = "ItemTypeId";
        public static final String COLUMN_NAME = "Name";
    }

    public static class GroceryList implements BaseColumns {
        public static final String TABLE_NAME = "GroceryList";
        public static final String COLUMN_NAME = "Name";
    }

    public static class ListItem implements BaseColumns {
        public static final String TABLE_NAME = "ListItem";
        public static final String COLUMN_QUANTITY = "Quantity";
        public static final String COLUMN_UNIT_TYPE_ID = "UnitTypeId";
        public static final String COLUMN_ITEM_ID = "ItemId";
        public static final String COLUMN_LIST_ID = "ListId";
    }
}
