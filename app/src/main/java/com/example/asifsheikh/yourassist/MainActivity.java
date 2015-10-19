package com.example.asifsheikh.yourassist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.asifsheikh.yourassist.Database.FeedReaderDbHelper;
import com.example.asifsheikh.yourassist.application.YourAssistApp;
import com.example.asifsheikh.yourassist.fragments.AddTaskFragment;
import com.example.asifsheikh.yourassist.fragments.HomeScreenFragment;
import com.example.asifsheikh.yourassist.home.NavigationDrawer_Activity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

public class MainActivity extends NavigationDrawer_Activity  implements HomeScreenFragment.OnFragmentInteractionListener{

    /* Client for accessing Google APIs */
    private GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(),"Welcome " +  YourAssistApp.getAppInstance().getCurrentPerson().getDisplayName(),
                Toast.LENGTH_LONG).show();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, HomeScreenFragment.newInstance(1))
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            if (mGoogleApiClient.isConnected()) {
                Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient);
                mGoogleApiClient.disconnect();
            }
            return true;
        }

        if (id == R.id.action_example) {
            Toast.makeText(getApplicationContext(),"Refreshing the list",Toast.LENGTH_LONG).show();
            HomeScreenFragment articleFrag = (HomeScreenFragment)
                    getSupportFragmentManager().findFragmentById(R.id.container);
            if (articleFrag != null) {
                articleFrag.refresh_task();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity.super.onBackPressed();
                        MainActivity.this.finish();
                    }
                }).create().show();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
