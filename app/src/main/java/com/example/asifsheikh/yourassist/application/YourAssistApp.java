package com.example.asifsheikh.yourassist.application;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
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

    List<Task> myList = new ArrayList<Task>();



    /* Client for accessing Google APIs */
    private  GoogleApiClient mGoogleApiClient;

    private Person currentPerson;
    private String Email;

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

    @Override
    public void onCreate() {
        super.onCreate();

        mRequestQueue = Volley.newRequestQueue(this);

        mYourAssistApp = this;
    }

    public  synchronized static  YourAssistApp getAppInstance(){

        return mYourAssistApp;

    }

    public YourAssistApp()
    {
        // Constructor hidden because this is a singleton
        // [START create_google_api_client]
        // Build GoogleApiClient with access to basic profile
        /*mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();*/
        // [END create_google_api_client]
    }

    public GoogleApiClient getmGoogleApiClient(){
        return mGoogleApiClient;
    }

    public  void  setmGoogleApiClient(GoogleApiClient mGoogleApiClient) {
        mGoogleApiClient = mGoogleApiClient;
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
