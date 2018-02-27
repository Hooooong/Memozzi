package com.hooooong.memozzi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Android Hong on 2018-02-25.
 */

public class DateUtil {

    private static final String TAG = "DateUtil";

    private static SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd a hh:mm", Locale.US);
    private static SimpleDateFormat dt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);

    public static String getCurrentDateTime() {
        return dt.format(System.currentTimeMillis());
    }

    public static String setSelectedDateTime(String dateTime) {

        Date date = null;
        try {
            date = dt2.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*
        String date = dateTime.substring(0, 10);
        String time = dateTime.substring(11);

        int hour = Integer.parseInt(time.split(":")[0]);
        int minute = Integer.parseInt(time.split(":")[1]);

        if (hour > 12) {
            return date + " PM " + (hour - 12) + ":" + minute;
        } else {
            return date + " AM " + time;
        }*/

        return dt.format(date);
    }
}
