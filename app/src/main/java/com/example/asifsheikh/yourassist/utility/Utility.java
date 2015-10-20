package com.example.asifsheikh.yourassist.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by asifsheikh on 19/10/15.
 */
public class Utility {

    public static String getDate(Date ndate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM/dd/yyyy", Locale.getDefault());
        return dateFormat.format(ndate);
    }

    public static int getPriorityofTask(String priority){
        if(priority.equals("low")){
            return 0;
        }
        else if(priority.equals("medium")){
            return 1;
        }
        else if(priority.equals("high")){
            return 2;
        }
        else {
            return 0;
        }
    }
}
