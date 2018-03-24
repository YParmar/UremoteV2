package com.example.rk.uremotev2.base;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Niel on 24/07/17.
 */

public class RetroClient {

    public static final String BASE_URL = "http://192.168.0.101:8080/";

    public static OkHttpClient getOkHttpClient(Context context) throws NotConnectedToInternetException {

        if (true){//NetworkUtils.isConnectedToInternet(context)) {

            return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                            //.addHeader("x-access-token", AppController.getmAccessToken())
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