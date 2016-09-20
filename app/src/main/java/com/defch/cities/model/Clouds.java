package com.defch.cities.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DiegoFranco on 9/9/16.
 */
public class Clouds implements Serializable
{
    private static final long serialVersionUID = -5063044745516494942L;

    @SerializedName("all")
    public int all;
}
