package com.example.rk.uremotev2.activities.homescreen;

import android.content.Context;

import com.example.rk.uremotev2.base.BasePresenter;

/**
 * Created by RK on 9/10/2017.
 */

public class HomeActivityPresenter extends BasePresenter<HomeActivityContract.HomeView>
        implements HomeActivityContract.HomePresenter {


    public HomeActivityPresenter(Context mContext) {
        super(mContext);
    }
}
