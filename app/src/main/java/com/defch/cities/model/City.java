package com.defch.cities.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by DiegoFranco on 9/9/16.
 */

public class City implements Serializable
{
    private static final long serialVersionUID = -1126277703164072005L;

    @SerializedName("coord")
    public Coordinates coord;

    @SerializedName("weather")
    public ArrayList<Weather> weather;

    @SerializedName("base")
    public String base;

    @SerializedName("main")
    public Main main;

    @SerializedName("wind")
    public Wind wind;

    @SerializedName("clouds")
    public Clouds clouds;

    @SerializedName("dt")
    public double dt;

    @SerializedName("sys")
    public Sys sys;

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("cod")
    public int cod;
}
