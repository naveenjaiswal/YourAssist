package com.example.asifsheikh.yourassist;


import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by asifsheikh on 1/9/15.
 */
public class YourAssistApp extends Application {

    private static YourAssistApp singleton;

    public static YourAssistApp getInstance() {
        if(singleton == null){
            synchronized (YourAssistApp.class){
                if(singleton == null){
                    singleton = new YourAssistApp();
                }
            }
        }
        return singleton;
    }

    private YourAssistApp(){

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
