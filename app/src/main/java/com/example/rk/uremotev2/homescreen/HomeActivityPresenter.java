package com.example.rk.uremotev2.homescreen;

import android.content.Context;

import com.example.rk.uremotev2.base.BasePresenter;
import com.example.rk.uremotev2.database.DatabaseHelper;
import com.example.rk.uremotev2.sharedpreference.PreferenceManager;

/**
 * Created by RK on 9/10/2017.
 */

public class HomeActivityPresenter extends BasePresenter<HomeActivityContract.HomeView>
        implements HomeActivityContract.HomePresenter {


    public HomeActivityPresenter(Context mContext) {
        super(mContext);
    }
}
