package com.example.rk.uremotev2.activities.homescreen;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;

import com.example.rk.uremotev2.activities.pairscreen.PairActivity;
import com.example.rk.uremotev2.base.BasePresenter;
import com.example.rk.uremotev2.base.RetroClient;
import com.example.rk.uremotev2.classes.AppConstants;
import com.example.rk.uremotev2.model.Datum;
import com.example.rk.uremotev2.model.FeedSwitchRequest;
import com.example.rk.uremotev2.model.FeedSwitchResponse;
import com.example.rk.uremotev2.services.BluetoothSendService;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RK on 9/10/2017.
 */

public class HomeActivityPresenter extends BasePresenter<HomeActivityContract.HomeView>
        implements HomeActivityContract.HomePresenter {

    private BluetoothSendService bluetoothSendService;

    public HomeActivityPresenter(Context mContext) {
        super(mContext);
    }

    @Override
    public void startConnection(BluetoothDevice device, UUID myUuidInsecure) {
        bluetoothSendService = new BluetoothSendService(mContext);
        bluetoothSendService.startClient(device, myUuidInsecure);
    }

    @Override
    public void enableDisableBluetooth() {

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            mView.showMessage("Your device does not have bluetooth capabilities");
        } else if (!bluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            mContext.startActivity(enableIntent);
            registerBluetoothReceiver();
        } else {

            String macAddress = mPreferenceManager.getStringForKey(AppConstants.MAC_ADDRESS, "");
            if (macAddress != null && !macAddress.isEmpty()) {
                BluetoothDevice device = bluetoothAdapter.getRemoteDevice(macAddress);
                startConnection(device, AppConstants.MY_UUID_INSECURE);
            } else {
                //TODO open pair activity here if bluetooth is on but no device is choosen to connect
            }
        }
    }

    @Override
    public void unregisterBluetoothBroadCastReceiver() {
        try {
            mContext.unregisterReceiver(bluetoothBroadcastReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void requestFragment(Fragment fragment) {
        mView.setRequestedFragment(fragment);
    }

    @Override
    public void sendData(String data) {
        if (bluetoothSendService != null) {
            bluetoothSendService.write(data.getBytes());
        } else {
            enableDisableBluetooth();
        }
    }

    @Override
    public void requestConnectionDialog() {
        mView.showConnectionDialog();
    }

    @Override
    public void callSwitchOnOffApiCall(String switchType, String value) {
        if (switchType != null && !switchType.isEmpty() && value != null && !value.isEmpty()) {

            try {

                Datum datum = new Datum();
                datum.setValue(value);

                FeedSwitchRequest feedSwitchRequest = new FeedSwitchRequest();
                feedSwitchRequest.setDatum(datum);

                RetroClient.getRetroClientWithInterceptor(RetroClient.getOkHttpClient(mContext))
                        .doFeedSwitchOnOFApiCall(switchType, feedSwitchRequest)
                        .enqueue(new Callback<FeedSwitchResponse>() {
                            @Override
                            public void onResponse(Call<FeedSwitchResponse> call, Response<FeedSwitchResponse> response) {
                                if (response.isSuccessful()) {
                                    mView.showMessage("Switch stated changed");
                                } else {
                                    mView.showMessage(response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<FeedSwitchResponse> call, Throwable t) {
                                mView.showMessage(t.getMessage());
                            }
                        });
            } catch (RetroClient.NotConnectedToInternetException e) {
                e.printStackTrace();
            }


        } else {
            mView.showMessage("Something went wrong");
        }
    }


    private final BroadcastReceiver bluetoothBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        mView.showMessage("Bluetooth turned off");
                        break;

                    case BluetoothAdapter.STATE_ON:
                        mView.showMessage("Bluetooth turned on");
                        mView.startPairActivityForResult();
                        break;
                }
            }
        }
    };

    private void registerBluetoothReceiver() {
        IntentFilter BtIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        mContext.registerReceiver(bluetoothBroadcastReceiver, BtIntent);
    }
}
