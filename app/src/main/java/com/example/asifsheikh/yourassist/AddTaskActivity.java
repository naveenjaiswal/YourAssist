package com.example.asifsheikh.yourassist;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.asifsheikh.yourassist.fragments.AddTaskFragment;
import com.example.asifsheikh.yourassist.home.NavigationDrawer_Activity;

/**
 * Created by Admin on 9/22/2015.
 */
public class AddTaskActivity extends NavigationDrawer_Activity implements  AddTaskFragment.OnFragmentInteractionListener {

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
            AddTaskFragment articleFrag = (AddTaskFragment)
                    getSupportFragmentManager().findFragmentById(R.id.add_task_fragment);
            Toast.makeText(getApplicationContext(), "Creating task",
                    Toast.LENGTH_LONG).show();

            if (articleFrag != null) {
                // If article frag is available, we're in two-pane layout...

                // Call a method in the ArticleFragment to update its content
                //articleFrag.date_picker();



            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
