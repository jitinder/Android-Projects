package com.example.android.habittrackerappproject.data;

import android.provider.BaseColumns;

/**
 * Created by Sidak Pasricha on 09-Aug-18.
 */

public final class HabitContract {

    public HabitContract() {
    }

    public static abstract class HabitEntry implements BaseColumns{

        public static final String TABLE_NAME = "habits";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "name";

        // Chosen using date + time components in Hypothetical UI
        public static final String COLUMN_HABIT_START = "start"; // Unix time for start time of Activity
        public static final String COLUMN_HABIT_STOP = "stop"; // Unix time for stop time of Activity

        public static final String COLUMN_HABIT_DESC = "description"; // Additional Description of Activity

    }
}
