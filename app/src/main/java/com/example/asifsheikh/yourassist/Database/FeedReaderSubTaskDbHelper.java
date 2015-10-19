package com.example.asifsheikh.yourassist.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.asifsheikh.yourassist.application.YourAssistApp;
import com.example.asifsheikh.yourassist.model.SubTask;
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
public class FeedReaderSubTaskDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "FeedReader";

    private static final String TEXT_TYPE = " TEXT";
    //private static final Date DATE_TYPE;
    private static final String INTEGER_TYPE = "INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderSubTaskContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderSubTaskContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderSubTaskContract.FeedEntry.COLUMN_NAME_TASK_ID + TEXT_TYPE + COMMA_SEP +
                    FeedReaderSubTaskContract.FeedEntry.COLUMN_NAME_SUB_TASK_ID + TEXT_TYPE + COMMA_SEP +
                    FeedReaderSubTaskContract.FeedEntry.COLUMN_NAME_SUB_TASK_NAME + TEXT_TYPE + COMMA_SEP +
                    FeedReaderSubTaskContract.FeedEntry.COLUMN_NAME_SUB_TASK_DESCRIPTION + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderSubTaskContract.FeedEntry.TABLE_NAME;

    public FeedReaderSubTaskDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
        //dropTaskTable();
        db.close();

    }

    public void initialize_subtask_db(){
        SQLiteDatabase db = this.getWritableDatabase();
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

    public void inserttask(SubTask new_subtask) {
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderSubTaskContract.FeedEntry.COLUMN_NAME_TASK_ID, "" + new_subtask.getTask_id());
        //values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TASK_ID, 0);
        values.put(FeedReaderSubTaskContract.FeedEntry.COLUMN_NAME_SUB_TASK_ID, "" + new_subtask.getSubtask_id());
        values.put(FeedReaderSubTaskContract.FeedEntry.COLUMN_NAME_SUB_TASK_NAME, new_subtask.getSubtask_name());
        values.put(FeedReaderSubTaskContract.FeedEntry.COLUMN_NAME_SUB_TASK_DESCRIPTION, new_subtask.getSubtask_description());

// Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FeedReaderSubTaskContract.FeedEntry.TABLE_NAME,
                null,
                values);
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+FeedReaderSubTaskContract.FeedEntry.TABLE_NAME+" where " + FeedReaderSubTaskContract.FeedEntry.COLUMN_NAME_TASK_ID +"='"+id+"'");
        db.close();
        //YourAssistApp.getAppInstance().decrementTaskNUmber();
    }

    public List<SubTask> getAllSubTask(int task_id) throws ParseException {
        List<SubTask> task_list;
        task_list = new ArrayList<SubTask>();
        String selectQuery = "SELECT  * FROM " + FeedReaderSubTaskContract.FeedEntry.TABLE_NAME + " where " + FeedReaderSubTaskContract.FeedEntry.COLUMN_NAME_TASK_ID+"="+task_id;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        Log.e("size of cursor ", " " + cursor.getCount());
        cursor.moveToFirst();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            SubTask mtask = new SubTask();
            mtask.setTask_id(Integer.parseInt(cursor.getString(1)));
            mtask.setSubtask_id(Integer.parseInt(cursor.getString(2)));
            Log.e("task id : ", cursor.getString(1));
            mtask.setSubtask_name(cursor.getString(3));
            mtask.setSubtask_description(cursor.getString(4));

            task_list.add(mtask);
        } //while (cursor.moveToNext());

        Log.e("Printing the all task ", "" + task_list.size());
        // return task list
        return task_list;
    }

    public SubTask getTaskfromDatabase(String task_id) throws ParseException {
        String selectQuery = "SELECT  * FROM " + FeedReaderSubTaskContract.FeedEntry.TABLE_NAME + " where " + FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID +"="+task_id+"";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        SubTask mtask = new SubTask();
        mtask.setTask_id(Integer.parseInt(cursor.getString(1)));
        mtask.setSubtask_id(Integer.parseInt(cursor.getString(2)));
        Log.e("task id : ", cursor.getString(1));
        mtask.setSubtask_name(cursor.getString(3));
        mtask.setSubtask_description(cursor.getString(4));
        Log.e("task : ", mtask.getSubtask_name());
        database.close();
        return mtask;
    }

    public int getnextsubtask_id(int taskid){
        int nextcount;
        String selectQuery = "SELECT  * FROM " + FeedReaderSubTaskContract.FeedEntry.TABLE_NAME + " where " + FeedReaderSubTaskContract.FeedEntry.COLUMN_NAME_TASK_ID+"="+taskid;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        Log.e("size of cursor ", " " + cursor.getCount());
        cursor.moveToFirst();
        if(cursor.getCount() == 0)
            return 1;
        else{
            cursor.moveToLast();
            nextcount = Integer.parseInt(cursor.getString(2));
        }

        return ++nextcount;
    }






}
