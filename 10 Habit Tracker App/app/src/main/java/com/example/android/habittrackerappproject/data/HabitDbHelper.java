package com.example.android.habittrackerappproject.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.habittrackerappproject.data.HabitContract.HabitEntry;

/**
 * Created by Sidak Pasricha on 09-Aug-18.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "habit.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA = " ,";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String PRIMARY_CONS = " PRIMARY KEY";
    private static final String AUTO_CONS = " AUTOINCREMENT";
    private static final String NOT_NULL_CONS = "NOT NULL";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +HabitEntry.TABLE_NAME+ " ("
            +HabitEntry._ID+ INTEGER_TYPE+ PRIMARY_CONS+ AUTO_CONS+ COMMA+
            HabitEntry.COLUMN_HABIT_NAME+ TEXT_TYPE+ NOT_NULL_CONS+ COMMA+
            HabitEntry.COLUMN_HABIT_START+ INTEGER_TYPE+ NOT_NULL_CONS+ COMMA+
            HabitEntry.COLUMN_HABIT_STOP+ INTEGER_TYPE+ NOT_NULL_CONS+ COMMA+
            HabitEntry.COLUMN_HABIT_DESC+ TEXT_TYPE+
            ");";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+HabitEntry.TABLE_NAME;

    public HabitDbHelper(Context context) {
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
