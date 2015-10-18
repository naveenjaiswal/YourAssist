package com.example.asifsheikh.yourassist;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.asifsheikh.yourassist.fragments.AddTaskFragment;
import com.example.asifsheikh.yourassist.fragments.TaskHomeFragment;
import com.example.asifsheikh.yourassist.home.NavigationDrawer_Activity;

/**
 * Created by Admin on 9/23/2015.
 */
public class TaskHomeActivity extends NavigationDrawer_Activity implements AddTaskFragment.OnFragmentInteractionListener {

    public static final String ARG_TASK_DETAILS = "task_details";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(getIntent().getStringExtra(ARG_TASK_DETAILS));
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, TaskHomeFragment.newInstance(1))
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
