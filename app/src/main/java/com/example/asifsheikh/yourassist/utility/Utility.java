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
}
