package com.example.asifsheikh.yourassist.Database;

import android.provider.BaseColumns;

/**
 * Created by asifsheikh on 19/10/15.
 */
public class FeedReaderSubTaskContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public FeedReaderSubTaskContract() {}

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "SubTaskTable";
        public static final String COLUMN_NAME_TASK_ID = "taskid";
        public static final String COLUMN_NAME_SUB_TASK_ID = "subtaskid";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_SUB_TASK_NAME = "task_name";
        public static final String COLUMN_NAME_SUB_TASK_DESCRIPTION = "task_description";
    }
}
