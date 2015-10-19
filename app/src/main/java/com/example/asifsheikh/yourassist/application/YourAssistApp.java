package com.example.asifsheikh.yourassist.application;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.asifsheikh.yourassist.Database.FeedReaderDbHelper;
import com.example.asifsheikh.yourassist.Database.FeedReaderSubTaskDbHelper;
import com.example.asifsheikh.yourassist.model.Task;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 9/20/2015.
 */
public class YourAssistApp extends Application {

    /* Singleton Object */
    private static YourAssistApp mYourAssistApp;
    private RequestQueue mRequestQueue;
    public static int numberofTasks;

    List<Task> myList = new ArrayList<Task>();



    /* Client for accessing Google APIs */
    private  GoogleApiClient mGoogleApiClient;
    

    private Person currentPerson;
    private String Email;
    private FeedReaderDbHelper mDBhelper;
    private FeedReaderSubTaskDbHelper mDbSubTaskHelper;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

   public Person getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
    }

    public void incrementTasknumber(){
        numberofTasks++;
    }

    public void decrementTaskNUmber(){
        numberofTasks--;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mRequestQueue = Volley.newRequestQueue(this);
        mDBhelper = new FeedReaderDbHelper(getApplicationContext());
        mDbSubTaskHelper = new FeedReaderSubTaskDbHelper((getApplicationContext()));
        mDbSubTaskHelper.initialize_subtask_db();
        //mDBhelper.dropTaskTable();
        numberofTasks = mDBhelper.getTotalTasks();
        //numberofTasks = 0;
        mYourAssistApp = this;
    }

    public  synchronized static  YourAssistApp getAppInstance(){

        return mYourAssistApp;

    }

    public YourAssistApp()
    {

    }

    public GoogleApiClient getmGoogleApiClient(){
        return this.mGoogleApiClient;
    }

    public  void  setmGoogleApiClient(GoogleApiClient mGoogleApiClient) {
        this.mGoogleApiClient = mGoogleApiClient;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


    public List<Task> getMyList() {
        return myList;
    }

    public void setMyList(List<Task> myList) {
        this.myList = myList;
    }


}
