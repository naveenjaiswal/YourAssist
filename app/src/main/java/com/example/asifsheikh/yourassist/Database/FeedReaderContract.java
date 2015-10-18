package com.example.asifsheikh.yourassist.Database;

import android.provider.BaseColumns;

/**
 * Created by asifsheikh on 19/10/15.
 */
public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "TaskTable";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TASK_NAME = "task_name";
        public static final String COLUMN_NAME_TASK_DESCRIPTION = "task_description";
        public static final String COLUMN_NAME_PRIORITY="priority";
        public static final String COLUMN_NAME_START_DATE = "start_date";
        public static final String COLUMN_NAME_END_DATE = "end_date";
        public static final String COLUMN_NAME_NULLABLE = "null";
    }

}
