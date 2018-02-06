package com.example.rk.uremotev2.activities.pairscreen;

import com.example.rk.uremotev2.base.IBasePresenter;
import com.example.rk.uremotev2.base.IBaseView;

public class PairActivityContract {

    interface PairView extends IBaseView {
    }

    interface PairPresenter extends IBasePresenter {
        void saveDeviceMacToPreference(String macAddress);
    }
}
