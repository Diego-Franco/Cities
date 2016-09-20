package com.defch.cities.network.request.request;

import android.content.Context;

import com.defch.cities.Const;
import com.defch.cities.network.request.interfaces.CityInterface;
import com.defch.cities.network.request.listener.BaseRetrofitRequestListener;
import com.defch.cities.network.request.result.DailyResult;

import retrofit2.Call;

/**
 * Created by DiegoFranco on 9/9/16.
 */

public class DailyRequest extends BaseRetrofitRequest<DailyResult>
{

    private BaseRetrofitRequestListener mBaseRetrofitRequestListener;
    private String cityId;

    public DailyRequest(Context context, String id)
    {
        super(DailyResult.class, context);
        this.cityId = id;
    }

    public void execute(BaseRetrofitRequestListener baseRetrofitRequestListener)
    {
        this.mBaseRetrofitRequestListener = baseRetrofitRequestListener;
        CityInterface cityInterface = retrofit.create(CityInterface.class);
        Call<DailyResult> callDaily = cityInterface.getDaily(cityId, Const.APP_ID);
        callDaily.enqueue(mBaseRetrofitRequestListener);
    }
}
