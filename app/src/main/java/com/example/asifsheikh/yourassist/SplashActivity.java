package com.example.asifsheikh.yourassist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.asifsheikh.yourassist.login.LoginActivity;

/**
 * Created by Admin on 9/21/2015.
 */
public class SplashActivity extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 5000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splashscreen);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this,LoginActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
