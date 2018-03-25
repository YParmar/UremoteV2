package com.example.rk.uremotev2.base;

import android.content.Context;

import com.example.rk.uremotev2.classes.AppConstants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    public static final String BASE_URL = "https://io.adafruit.com/api/v2/YParmar/";

    public static OkHttpClient getOkHttpClient(Context context) throws NotConnectedToInternetException {

        if (true) {//NetworkUtils.isConnectedToInternet(context)) {

            return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("X-AIO-Key", AppConstants.ADAFRUIT_API_KEY)
                            .build();
                    return chain.proceed(newRequest);
                }
            }).build();

        } else {
            throw new NotConnectedToInternetException(context);
        }
    }

    /*public static OkHttpClient getOkHttpClient() {

        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("x-access-token", AppController.getmAccessToken()).build();
                return chain.proceed(newRequest);
            }
        }).build();
    }*/


    public static RetrofitApiService getRetroClientWithInterceptor(OkHttpClient client) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RetrofitApiService.class);
    }

    public static class NotConnectedToInternetException extends Exception {

        public NotConnectedToInternetException(final Context context) {
            //NetworkUtils.showConnectionDialog(context);
        }
    }
}