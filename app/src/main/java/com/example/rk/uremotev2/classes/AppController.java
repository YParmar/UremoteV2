package com.example.rk.uremotev2.classes;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

public class AppController extends Application {

    private static Application mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static Context getAppContext() {
        return mInstance;
    }
}
