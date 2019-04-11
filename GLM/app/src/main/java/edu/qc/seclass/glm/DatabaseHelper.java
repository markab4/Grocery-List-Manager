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
        for(int i = 0; i < GroceryListContract.SQL_CREATE_ENTRIES.length; i++) {
            db.execSQL(GroceryListContract.SQL_CREATE_ENTRIES[i]);
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

    public long createNewList(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GroceryListContract.GroceryList.COLUMN_NAME, name);
        return db.insert(GroceryListContract.GroceryList.TABLE_NAME, null, values);
    }

    public void renameListByID(Long id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GroceryListContract.GroceryList.COLUMN_NAME, name);

        String selection = GroceryListContract.GroceryList._ID + " = ?";
        String[] selectionArgs = { id.toString() };

        db.update(GroceryListContract.GroceryList.TABLE_NAME, values, selection, selectionArgs);
    }

    public void deleteListByID(Long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = GroceryListContract.GroceryList._ID + " = ?";
        String[] selectionArgs = { id.toString() };

        db.delete(GroceryListContract.GroceryList.TABLE_NAME, selection, selectionArgs);
    }



}