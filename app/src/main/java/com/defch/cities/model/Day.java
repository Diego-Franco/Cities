package com.defch.cities.model;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by DiegoFranco on 9/9/16.
 */
public class Day implements Comparable<Day>
{
    @SerializedName("main")
    public Main main;

    @SerializedName("weather")
    public ArrayList<Weather> weather;

    @SerializedName("dt_txt")
    public String date;

    @Override
    public int compareTo(Day day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(date).compareTo(sdf.parse(day.date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
