package com.example.android.inventoryappproject.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.inventoryappproject.data.StockContract.StockEntry;

/**
 * Created by Sidak Pasricha on 12-Aug-18.
 */

public class StockDbHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "inventory.db";
    public static final int DB_VERSION = 1;

    private static final String TYPE_TEXT = " TEXT";
    private static final String TYPE_INTEGER = " INTEGER";
    private static final String COMMA = ", ";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + StockEntry.TABLE_NAME + " (" +
            StockEntry._ID + TYPE_INTEGER + " PRIMARY KEY" + COMMA +
            StockEntry.COLUMN_ITEM_NAME + TYPE_TEXT + " NON NULL" + COMMA +
            StockEntry.COLUMN_ITEM_PRICE + TYPE_TEXT + " NON NULL" + COMMA +
            StockEntry.COLUMN_ITEM_QUANTITY + TYPE_INTEGER + " NON NULL" + COMMA +
            StockEntry.COLUMN_ITEM_SUPPLIER + TYPE_TEXT + " NON NULL" + COMMA +
            StockEntry.COLUMN_ITEM_PICTURE + TYPE_TEXT + ");";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " +StockEntry.TABLE_NAME+ ";";

    public StockDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
