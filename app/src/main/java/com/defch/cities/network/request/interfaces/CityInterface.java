package com.defch.cities.network.request.interfaces;

import com.defch.cities.Const;
import com.defch.cities.network.request.result.CityResult;
import com.defch.cities.network.request.result.DailyResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DiegoFranco on 9/9/16.
 */

public interface CityInterface
{
    @GET(Const.GROUP_ID)
    Call<CityResult> getCities(@Query("id") String id,@Query("units") String unit, @Query("appid") String appId);

    @GET(Const.DAILY)
    Call<DailyResult> getDaily(@Query("id") String id, @Query("appid") String appId);
}
