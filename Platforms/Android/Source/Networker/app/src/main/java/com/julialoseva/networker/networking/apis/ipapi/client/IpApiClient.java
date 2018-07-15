package com.julialoseva.networker.networking.apis.ipapi.client;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.julialoseva.networker.networking.apis.ipapi.response.GetIpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class IpApiClient {

    private Retrofit retrofit;

    private static final String BASE_URL = "http://ip-api.com";

    public IpApiClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public void getIp(final OnChangeListener listener) {
        listener.onStarted();

        Api api = retrofit.create(Api.class);

        Call<GetIpResponse> call = api.getIp();

        call.enqueue(
                new Callback<GetIpResponse>() {
                    @Override
                    public void onResponse(Call<GetIpResponse> call, Response<GetIpResponse> response) {
                        GetIpResponse getIpResponse = response.body();
                        listener.onSuccess(getIpResponse);
                    }

                    @Override
                    public void onFailure(Call<GetIpResponse> call, Throwable t) {
                        listener.onFailed();
                    }
                }
        );

    }

    public interface Api {
        @GET("/json")
        Call<GetIpResponse> getIp();
    }

    public interface OnChangeListener {
        void onStarted();
        void onSuccess(GetIpResponse response);
        void onFailed();
    }

}
