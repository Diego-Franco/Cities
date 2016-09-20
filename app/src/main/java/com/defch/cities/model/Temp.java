package com.defch.cities.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DiegoFranco on 9/9/16.
 */
public class Temp
{
    @SerializedName("day")
    public double day;

    @SerializedName("min")
    public double min;

    @SerializedName("max")
    public double max;

    @SerializedName("night")
    public double night;

    @SerializedName("eve")
    public double eve;

    @SerializedName("morn")
    public double morn;
}
