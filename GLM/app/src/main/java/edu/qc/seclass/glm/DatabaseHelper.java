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
        db.execSQL(GroceryListContract.SQL_CREATE_ENTRIES);

        // Import default rows
        ContentValues values1 = new ContentValues();
        values1.put(GroceryListContract.GroceryList.COLUMN_NAME, "Test1");
        db.insert(GroceryListContract.GroceryList.TABLE_NAME, null, values1);

        ContentValues values2 = new ContentValues();
        values2.put(GroceryListContract.GroceryList.COLUMN_NAME, "Test2");
        db.insert(GroceryListContract.GroceryList.TABLE_NAME, null, values2);

        ContentValues values3 = new ContentValues();
        values3.put(GroceryListContract.GroceryList.COLUMN_NAME, "Test3");
        db.insert(GroceryListContract.GroceryList.TABLE_NAME, null, values3);

        ContentValues values4 = new ContentValues();
        values4.put(GroceryListContract.GroceryList.COLUMN_NAME, "Test4");
        db.insert(GroceryListContract.GroceryList.TABLE_NAME, null, values4);

        ContentValues values5 = new ContentValues();
        values5.put(GroceryListContract.GroceryList.COLUMN_NAME, "Test5");
        db.insert(GroceryListContract.GroceryList.TABLE_NAME, null, values5);

        ContentValues values6 = new ContentValues();
        values6.put(GroceryListContract.GroceryList.COLUMN_NAME, "Test6");
        db.insert(GroceryListContract.GroceryList.TABLE_NAME, null, values6);
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

    public long createNewList(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GroceryListContract.GroceryList.COLUMN_NAME, name);
        return db.insert(GroceryListContract.GroceryList.TABLE_NAME, null, values);
    }

    // Get items in list ID
    // Get item types
    // Get items with item type ID
}