package com.defch.cities.network.request.request;

import android.content.Context;

import com.defch.cities.Const;
import com.defch.cities.network.request.result.BaseRetrofitResult;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DiegoFranco on 9/9/16.
 */

public abstract class BaseRetrofitRequest<Result extends BaseRetrofitResult>
{
    protected Retrofit retrofit;
    protected OkHttpClient.Builder okHttpClient;

    public BaseRetrofitRequest(Class<Result> result, Context context) {

        okHttpClient = new OkHttpClient().newBuilder();

        retrofit = new Retrofit.Builder().baseUrl(Const.URL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build()).build();
    }
}
