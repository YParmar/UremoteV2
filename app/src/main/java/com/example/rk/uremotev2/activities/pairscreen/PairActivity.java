package com.example.rk.uremotev2.activities.pairscreen;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.rk.uremotev2.R;
import com.example.rk.uremotev2.activities.homescreen.HomeActivity;
import com.example.rk.uremotev2.adapters.PairDeviceAdapter;
import com.example.rk.uremotev2.base.BaseActivity;
import com.example.rk.uremotev2.classes.AppConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PairActivity extends BaseActivity<PairActivityPresenter> implements PairActivityContract.PairView,
        PairDeviceAdapter.BluetoothListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.pair_recyclerView)
    RecyclerView pairRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPresenter = new PairActivityPresenter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair);
        ButterKnife.bind(this);

        initActionBar(toolbar, true, "Paired devices");

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> bluetoothDevices = bluetoothAdapter.getBondedDevices();
        List<BluetoothDevice> deviceList = new ArrayList<>(bluetoothDevices);

        PairDeviceAdapter adapter = new PairDeviceAdapter(this, deviceList, this);
        pairRecyclerView.setHasFixedSize(true);
        pairRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pairRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDeviceSelected(String macAddress) {

        mPresenter.saveDeviceMacToPreference(macAddress);
        Intent resultIntent = new Intent();
        resultIntent.putExtra(AppConstants.MAC_ADDRESS, macAddress);
        setResult(HomeActivity.BT_DEVICE_RESULT_CODE, resultIntent);
        finish();

    }
}
