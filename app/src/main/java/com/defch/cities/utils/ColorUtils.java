package com.defch.cities.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.defch.cities.R;

/**
 * Created by DiegoFranco on 9/9/16.
 */

public class ColorUtils
{
    /**
     * create the values for hot and cold temperature
     */
    private static final double HOT_TEMP = 78.0;
    private static final double COLD_TEMP = 65.0;

    //get the temperature and return the color from the colors resource
    public static int getBackgroundColor(Context context, double temp)
    {
        int color = Integer.MIN_VALUE;
        if(temp > HOT_TEMP)
        {
            color = ContextCompat.getColor(context, R.color.red);
        }
        else if(temp < COLD_TEMP)
        {
            color = ContextCompat.getColor(context, R.color.blue);
        }
        else if(temp > COLD_TEMP && temp < HOT_TEMP)
        {
            color = ContextCompat.getColor(context, R.color.peach);
        }

        return color;
    }
}
