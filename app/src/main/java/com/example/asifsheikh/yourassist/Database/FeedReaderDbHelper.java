package com.example.asifsheikh.yourassist.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.asifsheikh.yourassist.application.YourAssistApp;
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
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "FeedReader.db";
    private Context mcontext;
    private static final String TEXT_TYPE = " TEXT";
    //private static final Date DATE_TYPE;
    private static final String INTEGER_TYPE = "INTEGER";
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
        mcontext = context;
        SQLiteDatabase db = this.getWritableDatabase();
        //dropTaskTable();
        db.close();

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

    public void dropTaskTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
        db.close();
    }

    public void inserttask(Task new_task) {
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID, "" + new_task.getTask_id());
        //values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TASK_ID, 0);
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
        FeedReaderSubTaskDbHelper mDbHelperSubtask = new FeedReaderSubTaskDbHelper(mcontext);
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
                mtask.setTask_id(Integer.parseInt(cursor.getString(1)));
                Log.e("task id : ", cursor.getString(1));
                mtask.setTask_name(cursor.getString(2));
                mtask.setTask_description(cursor.getString(3));
                mtask.setPriority(cursor.getString(4));
                Date dt = sdf.parse(cursor.getString(5));
                mtask.setStart_date(dt);
                dt = sdf.parse(cursor.getString(6));
                mtask.setDue_date(dt);
                mtask.setTotal_number_of_subtask(mDbHelperSubtask.getTotalSubtaskforTask(Integer.parseInt(cursor.getString(1))));
                mtask.setComplete_subtask(mDbHelperSubtask.getCompletedSubTaskforTask(Integer.parseInt(cursor.getString(1))));
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

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + FeedReaderContract.FeedEntry.TABLE_NAME + " where " + FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID + "='" + id + "'");
        db.close();
        //YourAssistApp.getAppInstance().decrementTaskNUmber();
    }

    public Task getTaskfromDatabase(String task_id) throws ParseException {
        String selectQuery = "SELECT  * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME + " where " + FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID +"='"+task_id+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        cursor.moveToFirst();
        Task mtask = new Task();
        mtask.setTask_id(Integer.parseInt(cursor.getString(1)));
        Log.e("task id : ", cursor.getString(1));
        mtask.setTask_name(cursor.getString(2));
        mtask.setTask_description(cursor.getString(3));
        mtask.setPriority(cursor.getString(4));
        //Log.e("startdate : ", cursor.getString(4));
        Date dt = sdf.parse(cursor.getString(5));
        mtask.setStart_date(dt);
        dt = sdf.parse(cursor.getString(6));
        mtask.setDue_date(dt);
        Log.e("task : ",mtask.getTask_name());
        database.close();
        return mtask;
    }

    public void updateTask(Task mtask){
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID, "" + mtask.getTask_id());
        //values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TASK_ID, 0);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TASK_NAME, mtask.getTask_name());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TASK_DESCRIPTION, mtask.getTask_description());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PRIORITY, mtask.getPriority());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_START_DATE, getDate(mtask.getStart_date()));
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_END_DATE, getDate(mtask.getDue_date()));

        db.update(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                values, FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID + "=" + mtask.getTask_id(), null);
        db.close();
    }

    public int getTotalTasks(){
        String selectQuery = "SELECT  * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME + " ";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        //database.close();
        return cursor.getCount();
    }

    public int getnextTaskId() {
        int nexttaskid;
        String selectQuery = "SELECT  * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME + " ";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0)
            return 1;
        else {
            cursor.moveToLast();
            nexttaskid = Integer.parseInt(cursor.getString(1));
        }

        database.close();
        return ++nexttaskid;
    }

}
