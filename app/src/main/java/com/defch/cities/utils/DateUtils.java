package com.defch.cities.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by DiegoFranco on 9/9/16.
 */

public class DateUtils
{
    /**
     * i created this class for parsing the dates from the request
     */

    public static String dateFormat = "yyyy-MM-dd hh:mm";
    public static String dayFormat = "yyyy-MM-dd hh:mm";
    public static String reFormat = "yyyy-MM-dd";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
    private static SimpleDateFormat daySimpleDateFormat = new SimpleDateFormat(dayFormat);

    private static  long currentDay = System.currentTimeMillis() % 1000;

    public static String formatDate(String date)
    {
        SimpleDateFormat reSFormat = new SimpleDateFormat(reFormat);
        try {
            return reSFormat.format(simpleDateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long convertDateStringToMilliseconds(String dateS)
    {
        Date date = null;
        try {
            date = daySimpleDateFormat.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String getDay(long milliseconds)
    {
       Calendar time = Calendar.getInstance();
       time.setTimeInMillis(milliseconds);
       return getDay(time.get(Calendar.DAY_OF_WEEK));
   }

    private static String getDay(int d)
    {
        String dayString = null;
        switch (d) {
            case 1:
                dayString ="Sunday";
                break;
            case 2:
                dayString  = "Monday";
                break;
            case 3:
                dayString = "Tuesday";
                break;
            case 4:
                dayString = "Wednesday";
                break;
            case 5:
                dayString = "Thursday";
                break;
            case 6:
                dayString = "Friday";
                break;
            case 7:
                dayString = "Saturday";
        }

        return dayString;
    }

}
