package edu.qc.seclass.glm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * @author      Sean Rodriguez <sean.rodriguez@outlook.com>
 *
 * Utility class that allows developer to interface with the database using defined functions to
 * query and update the database.
 *
 * @version     1.0
 * @since       1.0
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GroceryList.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Future update: Implement data migration
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Executes all the SQL statements in SQL_CREATE_ENTRIES
        for(int i = 0; i < GroceryListContract.SQL_CREATE_ENTRIES.length; i++) {
            db.execSQL(GroceryListContract.SQL_CREATE_ENTRIES[i]);
        }

        // Insert all the values in DEFAULT_ITEM_TYPES
        for(int i = 0; i < GroceryListContract.DEFAULT_ITEM_TYPES.length; i++) {
            ContentValues values = new ContentValues();
            values.put(GroceryListContract.ItemType.COLUMN_NAME,
                    GroceryListContract.DEFAULT_ITEM_TYPES[i]);
            db.insert(GroceryListContract.ItemType.TABLE_NAME, null, values);
        }

        // Insert all the values in DEFAULT_UNIT_TYPES
        for(int i = 0; i < GroceryListContract.DEFAULT_UNIT_TYPES.length; i++) {
            ContentValues values = new ContentValues();
            values.put(GroceryListContract.UnitType.COLUMN_NAME,
                    GroceryListContract.DEFAULT_UNIT_TYPES[i]);
            db.insert(GroceryListContract.UnitType.TABLE_NAME, null, values);
        }

        Item[] items = {
                new Item(1, "Apples", 1, ""),
                new Item(1, "Bananas", 1, ""),
                new Item(1, "Strawberries", 1, ""),
                new Item(1, "Grapes", 1, ""),
                new Item(1, "Pineapple", 1, ""),
                new Item(1, "Mango", 1, ""),

                new Item(1, "Broccoli", 2, ""),
                new Item(1, "Celery", 2, ""),
                new Item(1, "Lettuce", 2, ""),
                new Item(1, "Artichoke", 2, ""),
                new Item(1, "Beets", 2, ""),
                new Item(1, "Green Beans", 2, ""),
                new Item(1, "Bok Choy", 2, ""),
                new Item(1, "Carrots", 2, ""),


                new Item(1, "Rice", 3, ""),
                new Item(1, "Bagels", 3, ""),
                new Item(1, "Noodles", 3, ""),
                new Item(1, "Pasta", 3, ""),
                new Item(1, "Crackers", 3, ""),
                new Item(1, "Bread", 3, ""),
                new Item(1, "Tortilla", 3, ""),
                new Item(1, "Oatmeal", 3, ""),

                new Item(1, "Milk", 4, ""),
                new Item(1, "Yogurt", 4, ""),
                new Item(1, "Ice Cream", 4, ""),
                new Item(1, "Cheddar Cheese", 4, ""),
                new Item(1, "Ricotta Cheese", 4, ""),
                new Item(1, "Cottage Cheese", 4, ""),
                new Item(1, "Soy Milk", 4, ""),

                new Item(1, "Chicken", 5, ""),
                new Item(1, "Chicken Wings", 5, ""),
                new Item(1, "Steak", 5, ""),
                new Item(1, "Pork Chops", 5, ""),
                new Item(1, "Ham", 5, ""),
                new Item(1, "Turkey", 5, ""),
                new Item(1, "Ground Beef", 5, ""),
                new Item(1, "Eggs", 5, ""),

                new Item(1, "Water", 6, ""),
                new Item(1, "Apple Juice", 6, ""),
                new Item(1, "Ice Tea", 6, ""),
                new Item(1, "Soda", 6, ""),
                new Item(1, "Wine", 6, ""),
                new Item(1, "Beer", 6, ""),
                new Item(1, "Coffee", 6, ""),
                new Item(1, "Tea", 6, ""),

                new Item(1, "Shrimp", 7, ""),
                new Item(1, "Tilapia", 7, ""),
                new Item(1, "Lobster", 7, ""),
                new Item(1, "Crab", 7, ""),
                new Item(1, "Oyster", 7, ""),
                new Item(1, "Sushi", 7, ""),
                new Item(1, "Seaweed", 7, ""),
                new Item(1, "Tuna", 7, ""),
        };

        // Insert all default items
        for(int i = 0; i < items.length; i++) {
            ContentValues values = new ContentValues();
            values.put(GroceryListContract.Item.COLUMN_NAME, items[i].getName());
            values.put(GroceryListContract.Item.COLUMN_TYPE_ID, items[i].getType().getID());
            db.insert(GroceryListContract.Item.TABLE_NAME, null, values);
        }
    }

    /**
     * Returns all list IDs in GroceryList in a ArrayList
     * @return ArrayList with all list IDs
     */
    public List getAllListIDs() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                GroceryListContract.GroceryList.COLUMN_NAME
        };

        Cursor cursor = db.query(
                GroceryListContract.GroceryList.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List<Long> itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(GroceryListContract.GroceryList._ID));
            itemIds.add(itemId);
        }

        cursor.close();
        return itemIds;
    }

    /**
     * Returns the list name that has the specified ID
     * @param ID The ID of the list
     * @return The name of the list
     */
    public String getListNameByID(long ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                GroceryListContract.GroceryList.COLUMN_NAME
        };

        String selection = GroceryListContract.GroceryList._ID + " = ?";
        String[] selectionArgs = { Long.toString(ID) };

        Cursor cursor = db.query(
                GroceryListContract.GroceryList.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        cursor.moveToNext();
        String listName = cursor.getString(
                cursor.getColumnIndexOrThrow(GroceryListContract.GroceryList.COLUMN_NAME));

        cursor.close();
        return listName;
    }

    /**
     * Creates a new list inside of GroceryList table
     * @param name The name of list being created
     * @return The ID of the list that was created
     */
    public long createNewList(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GroceryListContract.GroceryList.COLUMN_NAME, name);
        return db.insert(GroceryListContract.GroceryList.TABLE_NAME, null, values);
    }

    /**
     * Renames the list with the specified ID
     * @param id The ID of the list being modified
     * @param name The new name of the list
     */
    public void renameListByID(Long id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GroceryListContract.GroceryList.COLUMN_NAME, name);

        String selection = GroceryListContract.GroceryList._ID + " = ?";
        String[] selectionArgs = { id.toString() };

        db.update(GroceryListContract.GroceryList.TABLE_NAME, values, selection, selectionArgs);
    }

    /**
     * Deletes the list with the specified ID
     * @param id The ID of the list being deleted
     */
    public void deleteListByID(Long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = GroceryListContract.GroceryList._ID + " = ?";
        String[] selectionArgs = { id.toString() };

        db.delete(GroceryListContract.GroceryList.TABLE_NAME, selection, selectionArgs);
    }


    /**
     * Gets all item types from the database
     * @return A List of ItemType objects
     */
    public List getAllItemTypes() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                GroceryListContract.ItemType.COLUMN_NAME
        };

        Cursor cursor = db.query(
                GroceryListContract.ItemType.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List<ItemType> itemTypes = new ArrayList<>();
        while(cursor.moveToNext()) {
            long id = cursor.getLong(
                    cursor.getColumnIndexOrThrow(GroceryListContract.ItemType._ID));
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(GroceryListContract.ItemType.COLUMN_NAME));
            itemTypes.add(new ItemType(id, name));
        }

        cursor.close();
        return itemTypes;
    }

    /**
     * Get the name of the item type with the specified ID
     * @param itemTypeID The id of the item type
     * @return The name of the item type
     */
    public String getItemTypeByID(Long itemTypeID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                GroceryListContract.ItemType.COLUMN_NAME
        };

        String selection = GroceryListContract.ItemType._ID + " = ?";
        String[] selectionArgs = { Long.toString(itemTypeID) };

        Cursor cursor = db.query(
                GroceryListContract.ItemType.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        String itemTypeName = "";
        if(cursor.moveToNext())
            itemTypeName = cursor.getString(
                cursor.getColumnIndexOrThrow(GroceryListContract.ItemType.COLUMN_NAME));

        cursor.close();
        return itemTypeName;
    }

    /**
     * Get all item from the specified itemTypeID
     * @param itemTypeID The item type id, that all items should be
     * @return A list of items with the specified itemTypeID
     */
    public List getItemsFromItemTypeID(long itemTypeID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                GroceryListContract.Item.COLUMN_NAME,
                GroceryListContract.Item.COLUMN_TYPE_ID
        };

        String selection = GroceryListContract.Item.COLUMN_TYPE_ID + " = ?";
        String[] selectionArgs = { Long.toString(itemTypeID) };

        Cursor cursor = db.query(
                GroceryListContract.Item.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        List<Item> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            long id = cursor.getLong(
                    cursor.getColumnIndexOrThrow(GroceryListContract.ItemType._ID));
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(GroceryListContract.ItemType.COLUMN_NAME));
            long typeID = cursor.getLong(
                    cursor.getColumnIndexOrThrow(GroceryListContract.ItemType._ID));
            items.add(new Item(id, name, new ItemType(typeID, getItemTypeByID(typeID))));
        }

        cursor.close();
        return items;
    }

    /**
     * Get all Items that match the item name given
     * @param search the item name specified
     * @return List of items that match the search
     */
    public List getSearchResultsByItemName(String search) {
        SQLiteDatabase db = this.getReadableDatabase();
        String projection[] = {
                BaseColumns._ID,
                GroceryListContract.Item.COLUMN_NAME,
                GroceryListContract.Item.COLUMN_TYPE_ID,
        };

        String selection = GroceryListContract.Item.COLUMN_NAME.toLowerCase() + " LIKE '% " + search.toLowerCase() + "' " +
                "OR " + GroceryListContract.Item.COLUMN_NAME.toLowerCase() + " LIKE '" + search.toLowerCase() + "%' " +
                "OR " + GroceryListContract.Item.COLUMN_NAME.toLowerCase() + " LIKE '% " + search.toLowerCase() + "%' " +
                "OR " + GroceryListContract.Item.COLUMN_NAME.toLowerCase() + " LIKE '" + search.toLowerCase() + "'";

        Cursor cursor = db.query(
                GroceryListContract.Item.TABLE_NAME,
                projection,
                selection,
                null,
                null,
                null,
                null
        );

        List<Item> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            long id = cursor.getLong(
                    cursor.getColumnIndexOrThrow(GroceryListContract.Item._ID));

            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(GroceryListContract.Item.COLUMN_NAME));

            long typeID = cursor.getLong(
                    cursor.getColumnIndexOrThrow(GroceryListContract.Item.COLUMN_TYPE_ID));

            items.add(new Item(id, name, typeID, getItemTypeByID(typeID)));
        }

        cursor.close();
        return items;
    }

    /**
     * Add a item to a list with the quantity and unit type ID
     * @param itemID Item that is being added
     * @param quantity The amount of the specified item
     * @param unitTypeID The unit type ID the quantity is by
     * @param listID The list ID the item is being added to
     * @return The id of the item in the list
     */
    public long addItemToList(long itemID, int quantity, long unitTypeID, long listID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GroceryListContract.ListItem.COLUMN_ITEM_ID, itemID);
        values.put(GroceryListContract.ListItem.COLUMN_QUANTITY, quantity);
        values.put(GroceryListContract.ListItem.COLUMN_UNIT_TYPE_ID, unitTypeID);
        values.put(GroceryListContract.ListItem.COLUMN_LIST_ID, listID);

        return db.insert(GroceryListContract.ListItem.TABLE_NAME, null, values);
    }

    /**
     * Get the Item ID by the specified name
     * @param name The name of the item
     * @return The id of the item
     */
    public Long getItemIdByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String projection[] = {
                BaseColumns._ID,
        };

        String selection = GroceryListContract.Item.COLUMN_NAME + " = ?";
        String[] selectionArgs = { name };

        Cursor cursor = db.query(
                GroceryListContract.Item.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        long id = -1;
        if(cursor.moveToNext()) {
            id = cursor.getLong(cursor.getColumnIndexOrThrow(GroceryListContract.Item._ID));
        }

        cursor.close();
        return id;
    }

    /**
     * Get the Item name by the specified ID
     * @param itemID The id of the item
     * @return The name of the item
     */
    public String getItemNameByID(Long itemID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String projection[] = {
                GroceryListContract.Item.COLUMN_NAME,
        };

        String selection = GroceryListContract.Item._ID + " = ?";
        String[] selectionArgs = { Long.toString(itemID) };

        Cursor cursor = db.query(
                GroceryListContract.Item.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        String name = "";
        if(cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndexOrThrow(GroceryListContract.Item.COLUMN_NAME));
        }

        cursor.close();
        return name;
    }

    /**
     * Get the Item name by the specified ID
     * @param itemID The id of the item
     * @return The name of the item
     */
    public Item getItemByID(Long itemID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String projection[] = {
                BaseColumns._ID,
                GroceryListContract.Item.COLUMN_NAME,
                GroceryListContract.Item.COLUMN_TYPE_ID
        };

        String selection = GroceryListContract.Item._ID + " = ?";
        String[] selectionArgs = { Long.toString(itemID) };

        Cursor cursor = db.query(
                GroceryListContract.Item.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        Item item = null;
        if(cursor.moveToNext()) {
             String name = cursor.getString(cursor.getColumnIndexOrThrow(GroceryListContract.Item.COLUMN_NAME));
             long typeID = cursor.getLong(cursor.getColumnIndexOrThrow(GroceryListContract.Item.COLUMN_TYPE_ID));
             String typeName = getItemTypeByID(typeID);

             item = new Item(itemID, name, typeID, typeName);

        }

        cursor.close();
        return item;
    }



    /**
     * Get all the unit types
     * @return A list of all the unit types
     */
    public List getAllUnitTypes() {
        SQLiteDatabase db = this.getReadableDatabase();
        String projection[] = {
                BaseColumns._ID,
                GroceryListContract.UnitType.COLUMN_NAME
        };

        Cursor cursor = db.query(
                GroceryListContract.UnitType.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List<UnitType> unitTypes = new ArrayList<>();

        while(cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(GroceryListContract.UnitType._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(GroceryListContract.UnitType.COLUMN_NAME));

            unitTypes.add(new UnitType(id, name));
        }

        return unitTypes;
    }

    /**
     * Get the unit type name by the specified ID
     * @param UnitTypeID The ID of the unit type
     * @return The name of the unit type
     */
    public String getUnitTypeNameByID(long UnitTypeID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String projection[] = {
                GroceryListContract.UnitType.COLUMN_NAME
        };

        String selection = GroceryListContract.UnitType._ID + " = ?";
        String[] selectionArgs = { Long.toString(UnitTypeID) };

        Cursor cursor = db.query(
                GroceryListContract.UnitType.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        String name = "";
        if(cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndexOrThrow(GroceryListContract.UnitType.COLUMN_NAME));
        }

        cursor.close();
        return name;
    }

    /**
     * Get the unit type ID by the specified name
     * @param name The name of the unit type
     * @return The ID of the unit type
     */
    public long getUnitTypeIdByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String projection[] = {
                BaseColumns._ID
        };

        String selection = GroceryListContract.UnitType.COLUMN_NAME + " = ?";
        String[] selectionArgs = { name };

        Cursor cursor = db.query(
                GroceryListContract.UnitType.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        long id = -1;
        if(cursor.moveToNext()) {
            id = cursor.getLong(cursor.getColumnIndexOrThrow(GroceryListContract.UnitType._ID));
        }

        cursor.close();
        return id;
    }

    /**
     * Creates a new item
     * @param name the name of the item
     * @param typeID the item type id
     * @return the id of the newly created item
     */
    public long createNewItem(String name, long typeID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GroceryListContract.Item.COLUMN_NAME, name);
        values.put(GroceryListContract.Item.COLUMN_TYPE_ID, typeID);
        return db.insert(GroceryListContract.Item.TABLE_NAME, null, values);
    }

    /**
     * Get all item from a list by list ID
     * @param listID The list ID where the items are
     * @return A list of all the item from the list
     */
    public List getGroceryItemsByListID(long listID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String projection[] = {
                BaseColumns._ID,
                GroceryListContract.ListItem.COLUMN_ITEM_ID,
                GroceryListContract.ListItem.COLUMN_QUANTITY,
                GroceryListContract.ListItem.COLUMN_UNIT_TYPE_ID
        };

        String selection = GroceryListContract.ListItem.COLUMN_LIST_ID + " = ?";
        String[] selectionArgs = { Long.toString(listID) };

        Cursor cursor = db.query(
                GroceryListContract.ListItem.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        List<GroceryItem> listItems = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemID = cursor.getLong(cursor.getColumnIndexOrThrow(GroceryListContract.ListItem.COLUMN_ITEM_ID));
            int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(GroceryListContract.ListItem.COLUMN_QUANTITY));
            long unitTypeID = cursor.getLong(cursor.getColumnIndexOrThrow(GroceryListContract.ListItem.COLUMN_UNIT_TYPE_ID));
            Item item = getItemByID(itemID);

            GroceryItem listItem = new GroceryItem(item, quantity, getUnitTypeNameByID(unitTypeID));
            listItems.add(listItem);
        }

        cursor.close();
        return listItems;
    }
}