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

        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(GroceryListContract.GroceryList._ID));
            itemIds.add(itemId);
        }

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
        return cursor.getString(
                cursor.getColumnIndexOrThrow(GroceryListContract.GroceryList.COLUMN_NAME));
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

        List itemTypes = new ArrayList<>();
        while(cursor.moveToNext()) {
            long id = cursor.getLong(
                    cursor.getColumnIndexOrThrow(GroceryListContract.ItemType._ID));
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(GroceryListContract.ItemType.COLUMN_NAME));
            itemTypes.add(new ItemType(id, name));
        }

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

        cursor.moveToNext();
        return cursor.getString(
                cursor.getColumnIndexOrThrow(GroceryListContract.ItemType.COLUMN_NAME));
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

        List items = new ArrayList<>();
        while(cursor.moveToNext()) {
            long id = cursor.getLong(
                    cursor.getColumnIndexOrThrow(GroceryListContract.ItemType._ID));
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(GroceryListContract.ItemType.COLUMN_NAME));
            long typeID = cursor.getLong(
                    cursor.getColumnIndexOrThrow(GroceryListContract.ItemType._ID));
            items.add(new Item(id, name, new ItemType(typeID, getItemTypeByID(typeID))));
        }

        return items;
    }

}