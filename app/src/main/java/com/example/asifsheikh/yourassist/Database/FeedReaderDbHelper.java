package com.example.asifsheikh.yourassist.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.asifsheikh.yourassist.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by asifsheikh on 19/10/15.
 */
public class FeedReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    private static final String TEXT_TYPE = " TEXT";
    //private static final Date DATE_TYPE;
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TASK_NAME + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TASK_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_PRIORITY + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_START_DATE + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_END_DATE + TEXT_TYPE +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.d("database", "task table Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void inserttask(Task new_task) {
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID, "");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TASK_NAME, new_task.getTask_name());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TASK_DESCRIPTION, new_task.getTask_description());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PRIORITY, new_task.getPriority());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_START_DATE, getDate(new_task.getStart_date()));
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_END_DATE, getDate(new_task.getDue_date()));

// Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                null,
                values);
        db.close();
    }

    public List<Task> getAllTask() throws ParseException {
        List<Task> task_list;
        task_list = new ArrayList<Task>();
        String selectQuery = "SELECT  * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME + " ";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        Log.e("size of cursor ", " " + cursor.getCount());
        cursor.moveToFirst();
       for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                Task mtask = new Task();
                mtask.setTask_name(cursor.getString(2));
                mtask.setTask_description(cursor.getString(3));
                mtask.setPriority(cursor.getString(4));
                //Log.e("startdate : ", cursor.getString(4));
                Date dt = sdf.parse(cursor.getString(5));
                mtask.setStart_date(dt);
                dt = sdf.parse(cursor.getString(6));
                mtask.setDue_date(dt);
               Log.e("task : ",mtask.getTask_name());
                task_list.add(mtask);
            } //while (cursor.moveToNext());

        Log.e("Printing the all task " , "" + task_list.size());
        // return task list
        return task_list;
    }

    private String getDate(Date ndate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM/dd/yyyy", Locale.getDefault());
        return dateFormat.format(ndate);
    }





}
