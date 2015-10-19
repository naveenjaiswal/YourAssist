package com.example.asifsheikh.yourassist;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.asifsheikh.yourassist.Database.FeedReaderSubTaskDbHelper;
import com.example.asifsheikh.yourassist.fragments.AddSubTaskFragment;
import com.example.asifsheikh.yourassist.fragments.AddTaskFragment;
import com.example.asifsheikh.yourassist.home.NavigationDrawer_Activity;
import com.example.asifsheikh.yourassist.model.SubTask;

/**
 * Created by asifsheikh on 19/10/15.
 */
public class SubTaskAddActivity extends NavigationDrawer_Activity implements AddSubTaskFragment.OnFragmentInteractionListener {

    private SubTask new_subtask;
    FeedReaderSubTaskDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new FeedReaderSubTaskDbHelper(getApplicationContext());
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, AddSubTaskFragment.newInstance(1))
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_done) {
            AddSubTaskFragment articleFrag = (AddSubTaskFragment)
                    getSupportFragmentManager().findFragmentById(R.id.container);


            if (articleFrag != null) {
                // If article frag is available, we're in two-pane layout...

                // Call a method in the ArticleFragment to update its content
                //articleFrag.date_picker();

                new_subtask = articleFrag.get_tasksub();
                //YourAssistApp.getAppInstance().getMyList().add(new_task);
                Log.d(new_subtask.getSubtask_name() + " " + new_subtask.getSubtask_description(), "new Task desription");
                Toast.makeText(getApplicationContext(), "Creating task",
                        Toast.LENGTH_LONG).show();
                mDbHelper = new FeedReaderSubTaskDbHelper(this);
                mDbHelper.inserttask(new_subtask);
                super.onBackPressed();
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
