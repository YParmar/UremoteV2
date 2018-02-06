package com.example.rk.uremotev2.activities.pairscreen;

import android.content.Context;

import com.example.rk.uremotev2.base.BasePresenter;
import com.example.rk.uremotev2.classes.AppConstants;

public class PairActivityPresenter extends BasePresenter<PairActivityContract.PairView> implements PairActivityContract.PairPresenter{

    public PairActivityPresenter(Context mContext) {
        super(mContext);
    }

    @Override
    public void saveDeviceMacToPreference(String macAddress) {
        mPreferenceManager.saveStringForKey(AppConstants.MAC_ADDRESS, macAddress);
    }
}
