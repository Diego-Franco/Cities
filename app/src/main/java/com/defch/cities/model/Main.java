package com.defch.cities.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DiegoFranco on 9/9/16.
 */
public class Main implements Serializable
{
    private static final long serialVersionUID = -864167889364501037L;

    @SerializedName("temp")
    public double temp;

    @SerializedName("pressure")
    public double pressure;

    @SerializedName("humidity")
    public double humidity;

    @SerializedName("temp_min")
    public double temp_min;

    @SerializedName("temp_max")
    public double temp_max;
}
