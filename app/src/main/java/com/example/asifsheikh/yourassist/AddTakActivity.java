package com.example.asifsheikh.yourassist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.asifsheikh.yourassist.application.YourAssistApp;
import com.example.asifsheikh.yourassist.fragments.AddTaskFragment;
import com.example.asifsheikh.yourassist.fragments.HomeScreenFragment;
import com.example.asifsheikh.yourassist.home.NavigationDrawer_Activity;

/**
 * Created by Admin on 9/22/2015.
 */
public class AddTakActivity extends NavigationDrawer_Activity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, AddTaskFragment.newInstance(1))
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.navigation_drawer_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            if (mGoogleApiClient.isConnected()) {
                Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient);
                mGoogleApiClient.disconnect();
            }
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }
}
