package com.defch.cities.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DiegoFranco on 9/9/16.
 */
public class Weather implements Serializable
{
    private static final long serialVersionUID = 6845072908903027698L;

    @SerializedName("id")
    public int id;

    @SerializedName("main")
    public String main;

    @SerializedName("description")
    public String description;

    @SerializedName("icon")
    public String icon;
}
