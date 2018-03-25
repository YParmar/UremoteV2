package com.example.rk.uremotev2.activities.homescreen;

import android.bluetooth.BluetoothDevice;
import android.support.v4.app.Fragment;

import com.example.rk.uremotev2.base.IBasePresenter;
import com.example.rk.uremotev2.base.IBaseView;

import java.util.UUID;

/**
 * Created by RK on 9/10/2017.
 */

public class HomeActivityContract {

    interface HomeView extends IBaseView {

        void startPairActivityForResult();

        void setRequestedFragment(Fragment fragment);

        void showConnectionDialog();
    }

    interface HomePresenter extends IBasePresenter {
        void startConnection(BluetoothDevice device, UUID myUuidInsecure);

        void enableDisableBluetooth();

        void unregisterBluetoothBroadCastReceiver();

        void requestFragment(Fragment fragment);

        void sendData(String s);

        void requestConnectionDialog();

        void callSwitchOnOffApiCall(String switchType, String value);
    }
}
