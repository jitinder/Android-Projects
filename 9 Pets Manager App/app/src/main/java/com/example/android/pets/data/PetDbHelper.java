package com.example.android.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.pets.data.PetContract.PetEntry;

/**
 * Created by Sidak Pasricha on 08-Aug-18.
 */

public class PetDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "shelter.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA = " ,";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String PRIMARY_CONS = " PRIMARY KEY";
    private static final String AUTO_CONS = " AUTOINCREMENT";
    private static final String NOT_NULL_CONS = "NOT NULL";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +PetEntry.TABLE_NAME+ " ("
            +PetEntry._ID+ INTEGER_TYPE+ PRIMARY_CONS+ AUTO_CONS+ COMMA+
            PetEntry.COLUMN_PET_NAME+ TEXT_TYPE+ NOT_NULL_CONS+ COMMA+
            PetEntry.COLUMN_PET_BREED+ TEXT_TYPE+ COMMA+
            PetEntry.COLUMN_PET_GENDER+ INTEGER_TYPE+ NOT_NULL_CONS+ COMMA+
            PetEntry.COLUMN_PET_WEIGHT+ INTEGER_TYPE+ NOT_NULL_CONS+ " DEFAULT 0 );";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+PetEntry.TABLE_NAME;

    public PetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
