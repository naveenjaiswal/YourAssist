package com.example.asifsheikh.yourassist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.asifsheikh.yourassist.Database.FeedReaderDbHelper;
import com.example.asifsheikh.yourassist.application.YourAssistApp;
import com.example.asifsheikh.yourassist.fragments.HomeScreenFragment;
import com.example.asifsheikh.yourassist.fragments.SubTaskDashBoardFragment;
import com.example.asifsheikh.yourassist.home.NavigationDrawer_Activity;
import com.example.asifsheikh.yourassist.model.Task;
import com.google.android.gms.plus.Plus;

import java.text.ParseException;

/**
 * Created by asifsheikh on 19/10/15.
 */
public class SubTaskDashboard extends NavigationDrawer_Activity implements SubTaskDashBoardFragment.OnFragmentInteractionListener{

    public static final String ARG_TASK_DETAILS = "task_details";
    public String task_id;
    private FeedReaderDbHelper mDbHelper;
    public static Task task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        task_id = getIntent().getStringExtra(ARG_TASK_DETAILS).toString();
        mDbHelper = new FeedReaderDbHelper(this);
        try {
            task = mDbHelper.getTaskfromDatabase(task_id);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.setTitle(task.getTask_name().toUpperCase());
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, SubTaskDashBoardFragment.newInstance(1))
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        /*//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            if (mGoogleApiClient.isConnected()) {
                Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient);
                mGoogleApiClient.disconnect();
            }
            return true;
        }*/

        if (id == R.id.action_example) {
            Toast.makeText(getApplicationContext(),"Refreshing the list",Toast.LENGTH_LONG).show();
            SubTaskDashBoardFragment articleFrag = (SubTaskDashBoardFragment)
                    getSupportFragmentManager().findFragmentById(R.id.container);
            if (articleFrag != null) {
                articleFrag.refresh_subtask();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
