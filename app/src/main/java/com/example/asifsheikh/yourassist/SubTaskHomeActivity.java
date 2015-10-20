package com.example.asifsheikh.yourassist;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.asifsheikh.yourassist.Database.FeedReaderSubTaskDbHelper;
import com.example.asifsheikh.yourassist.fragments.SubTaskHomeFragment;
import com.example.asifsheikh.yourassist.home.NavigationDrawer_Activity;
import com.example.asifsheikh.yourassist.model.SubTask;

import java.text.ParseException;

/**
 * Created by asifsheikh on 19/10/15.
 */
public class SubTaskHomeActivity extends NavigationDrawer_Activity implements SubTaskHomeFragment.OnFragmentInteractionListener{

    public static SubTask msubtask;
    public static final String ARG_TASK_DETAILS = "task_details";
    public static final String ARG_SUB_TASK_DETAILS = "sub_task_details";
    public String task_id;
    public String sub_task_id;
    private FeedReaderSubTaskDbHelper mDbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        task_id = getIntent().getStringExtra(ARG_TASK_DETAILS).toString();
        sub_task_id = getIntent().getStringExtra(ARG_SUB_TASK_DETAILS).toString();
        mDbHelper = new FeedReaderSubTaskDbHelper(this);
        try {
            msubtask = mDbHelper.getSubTaskfromDatabase(task_id,sub_task_id);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.setTitle(msubtask.getSubtask_name().toUpperCase());
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, SubTaskHomeFragment.newInstance(1))
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.add_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
