package com.example.rk.uremotev2.base;

import android.content.Context;

import com.example.rk.uremotev2.database.DatabaseHelper;
import com.example.rk.uremotev2.sharedpreference.PreferenceManager;

/**
 * Created by RK on 9/10/2017.
 */

public class BasePresenter<V extends IBaseView> {

    protected V mView;
    protected Context mContext;
    protected DatabaseHelper mDbHelper;
    protected PreferenceManager mPreferenceManager;

    public BasePresenter(Context mContext) {
        this.mContext = mContext;
        //this.mDbHelper = ;
        this.mPreferenceManager = new PreferenceManager(mContext) ;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public void onDestroy() {
        mView = null;
    }

    protected Context getContext() {
        return mContext;
    }

    public void attachView(V mView){
        this.mView = mView;
    }

}
