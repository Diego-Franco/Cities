package com.defch.cities.utils;

/**
 * Created by DiegoFranco on 9/9/16.
 */

public class TempUtils
{
    /**
     * get the temperature value and convert to F
     * @param temp
     * @return
     */
    public static double getFahrenheit(double temp)
    {
        double f = (((temp - 273) * 9d/5) + 32);
        return f;
    }
}
