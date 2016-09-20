package com.defch.cities.network.request.listener;

import android.content.Context;
import android.util.Log;
import android.view.Window;

import com.defch.cities.R;
import com.defch.cities.network.request.result.BaseRetrofitResult;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by DiegoFranco on 9/9/16.
 * This class is created for use in a complete project, for get the response and parsing any possible error
 * from the API
 */

public abstract class BaseRetrofitRequestListener<T extends BaseRetrofitResult> implements Callback<T>
{
    protected final Context context;
    protected final Window mWindow;

    public BaseRetrofitRequestListener(Context context) {
        this.context = context;
        mWindow = null;
    }

    public BaseRetrofitRequestListener(Context context, Window window) {
        this.context = context;
        mWindow = window;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response)
    {
        try {
            if (response.isSuccessful()) {
                onRequestSuccessful(response);
            } else {
                if (response.code() == HttpURLConnection.HTTP_SERVER_ERROR) {
                    Log.e(TAG, "500 Error : " + (response.message() != null && !response.message().isEmpty() ? response.message() : "Unknown Server Error"));
                    onRequestFailed(response.code(), context.getResources().getString(R.string.internal_server_error));
                    return;
                }

                if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED || response.code() == 401) //Catch a 401
                {
                    Log.e(TAG, "401 base listener : " + (response.errorBody() != null && !response.errorBody().toString().isEmpty() ? response.errorBody().string() : "Unable to get error message"));
                } else if (response.code() == HttpURLConnection.HTTP_BAD_REQUEST) //Catch a 400
                {
                    Log.e(TAG, "400 base listener : " + (response.errorBody() != null && !response.errorBody().toString().isEmpty() ? response.errorBody().string() : "Unable to get error message"));
                }
                else if(response.code() == HttpURLConnection.HTTP_NOT_FOUND)
                {
                    Log.e(TAG, "404 Error : " + (response.message() != null && !response.message().isEmpty() ? response.message() : "404 Not Found"));
                    onRequestFailed(response.code(), response.message() != null && !response.message().isEmpty() ? response.message() : "404 Not Found");
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "general base listener : " + (e.getMessage() != null ? e.getMessage() : "blank error message."));
            onRequestFailed(-1, e.getMessage());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }

    public abstract void onRequestFailed(int code, String errorMessage);

    public abstract void onRequestSuccessful(Response<T> response);
}
