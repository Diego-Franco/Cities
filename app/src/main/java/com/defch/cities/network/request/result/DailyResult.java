package com.defch.cities.network.request.result;

import com.defch.cities.model.City;
import com.defch.cities.model.Day;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by DiegoFranco on 9/9/16.
 */

public class DailyResult extends BaseRetrofitResult implements Serializable
{
    private static final long serialVersionUID = 5772241683615569012L;

    @SerializedName("city")
    public City city;

    @SerializedName("cod")
    public int cod;

    @SerializedName("message")
    public double message;

    @SerializedName("cnt")
    public int cnt;

    @SerializedName("list")
    public ArrayList<Day> days;
}
