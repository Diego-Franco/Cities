package com.defch.cities.network.request.request;

import android.content.Context;

import com.defch.cities.Const;
import com.defch.cities.network.request.interfaces.CityInterface;
import com.defch.cities.network.request.listener.BaseRetrofitRequestListener;
import com.defch.cities.network.request.result.CityResult;

import retrofit2.Call;

/**
 * Created by DiegoFranco on 9/9/16.
 */

public class CityRequest extends BaseRetrofitRequest<CityResult> {

    private BaseRetrofitRequestListener mBaseRetrofitRequestListener;
    private String citiesId;

    public CityRequest(Context context, String citiesId) {
        super(CityResult.class, context);
        this.citiesId = citiesId;
    }

    public void execute(BaseRetrofitRequestListener baseRetrofitRequestListener)
    {
        this.mBaseRetrofitRequestListener = baseRetrofitRequestListener;
        CityInterface cityInterface = retrofit.create(CityInterface.class);
        Call<CityResult> callCities = cityInterface.getCities(citiesId, Const.UNITS_VALUE, Const.APP_ID);
        callCities.enqueue(mBaseRetrofitRequestListener);
    }
}
