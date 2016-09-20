package com.defch.cities.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DiegoFranco on 9/9/16.
 */
public class Coordinates implements Serializable
{
    private static final long serialVersionUID = 7694065645768991100L;

    @SerializedName("lon")
    public double longitude;

    @SerializedName("lat")
    public double latitude;
}
