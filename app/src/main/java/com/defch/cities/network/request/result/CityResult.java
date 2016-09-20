package com.defch.cities.network.request.result;

import com.defch.cities.model.City;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by DiegoFranco on 9/9/16.
 */

public class CityResult extends BaseRetrofitResult implements Serializable
{

    private static final long serialVersionUID = -2057124733690187286L;

    @SerializedName("cnt")
    public int cnt;

    @SerializedName("list")
    public ArrayList<City> list;
}
