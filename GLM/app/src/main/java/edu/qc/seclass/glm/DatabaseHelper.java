package edu.qc.seclass.glm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GroceryList.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Present in future updates
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(GroceryListContract.SQL_CREATE_ENTRIES);

        // Import default rows
        // db.insert()
    }

    // TODO: Implement database helper functions
    // Get all list names
    // Get items in list ID
    // Get item types
    // Get items with item type ID
}