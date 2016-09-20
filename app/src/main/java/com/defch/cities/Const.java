package com.defch.cities;

/**
 * Created by DiegoFranco on 9/9/16.
 */

public class Const
{

    /**
     * i created the base url, and parts of the url for get the 5 cities and the forecast of the city
     * i used the url for get the image from the url from the API
     */
    public static final String URL = "http://api.openweathermap.org/data/2.5/";
    public static final String GROUP_ID = "group?";
    public static final String DAILY = "forecast?";
    public static final String ICON_URL = "http://openweathermap.org/img/w/";

    /**
     * This is my APP key for the API
     */

    public static final String APP_ID = "7a1d75c9895a75aa3f55008890c00c22";

    /**
     * i need to use this value for get the imperial units from the request and i converted from kelvin to F
     */
    public static final String UNITS_VALUE = "imperial";

}
