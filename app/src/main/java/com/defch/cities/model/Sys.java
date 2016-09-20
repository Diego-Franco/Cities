package com.defch.cities.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DiegoFranco on 9/9/16.
 */
public class Sys implements Serializable
{
    private static final long serialVersionUID = 4008344583598225460L;

    @SerializedName("type")
    public double type;

    @SerializedName("id")
    public int id;

    @SerializedName("message")
    public double message;

    @SerializedName("country")
    public String country;

    @SerializedName("sunrise")
    public double sunrise;

    @SerializedName("sunset")
    public double sunset;
}
