package com.example.android.habittrackerappproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.habittrackerappproject.data.HabitContract;
import com.example.android.habittrackerappproject.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper helper = new HabitDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView dbData = (TextView) findViewById(R.id.db_text);

        insertToDB();
        dbData.setText(readFromDB());
    }

    public String readFromDB(){
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(HabitContract.HabitEntry.TABLE_NAME, null, null, null, null, null, null);
        StringBuilder stringBuilder = new StringBuilder();

        try{
            stringBuilder.append("The habits table contains " + cursor.getCount() + " habits.\n\n");
            stringBuilder.append(
                    HabitContract.HabitEntry._ID  + " - " +
                            HabitContract.HabitEntry.COLUMN_HABIT_NAME + " - " +
                            HabitContract.HabitEntry.COLUMN_HABIT_START + " - " +
                            HabitContract.HabitEntry.COLUMN_HABIT_STOP + " - " +
                            HabitContract.HabitEntry.COLUMN_HABIT_DESC + " \n\n ");
            while (cursor.moveToNext()){
                stringBuilder.append(
                        cursor.getInt(cursor.getColumnIndex(HabitContract.HabitEntry._ID)) + " - " +
                                cursor.getString(cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_NAME)) + " - " +
                                cursor.getInt(cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_START)) + " - " +
                                cursor.getInt(cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_STOP)) + " - " +
                                cursor.getString(cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_DESC)));
            }
        } finally {
            cursor.close();
        }
        return stringBuilder.toString();
    }

    public void insertToDB(){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, "Walking dog");
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_START, 1533820022); // Thursday, 09-Aug-18 13:07:02 UTC
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_STOP, 1533830022); // Thursday, 09-Aug-18 15:53:42 UTC
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_DESC, "Was calling a friend simultaneously");

        db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);
    }
}
