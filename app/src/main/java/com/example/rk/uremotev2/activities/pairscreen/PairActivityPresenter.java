package com.example.rk.uremotev2.activities.pairscreen;

import android.content.Context;

import com.example.rk.uremotev2.base.BasePresenter;

public class PairActivityPresenter extends BasePresenter<PairActivityContract.PairView> implements PairActivityContract.PairPresenter{
    public PairActivityPresenter(Context mContext) {
        super(mContext);
    }
}
