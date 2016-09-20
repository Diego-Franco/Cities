package com.defch.cities.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DiegoFranco on 9/9/16.
 */
public class Wind implements Serializable
{
    private static final long serialVersionUID = -1483593454815756062L;

    @SerializedName("speed")
    public double speed;

    @SerializedName("deg")
    public double deg;
}
