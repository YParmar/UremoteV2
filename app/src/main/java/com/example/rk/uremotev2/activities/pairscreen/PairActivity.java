package com.example.rk.uremotev2.activities.pairscreen;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.example.rk.uremotev2.Model.Device;
import com.example.rk.uremotev2.R;
import com.example.rk.uremotev2.adapters.PairDeviceAdapter;
import com.example.rk.uremotev2.base.BaseActivity;
import com.example.rk.uremotev2.classes.AppController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PairActivity extends BaseActivity<PairActivityPresenter> implements PairActivityContract.PairView {

    @BindView(R.id.pair_recyclerView)
    RecyclerView pairRecyclerView;

    private BluetoothAdapter bluetoothAdapter;
    private List<Device> deviceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPresenter = new PairActivityPresenter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair);
        ButterKnife.bind(this);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //IntentFilter pairFilter = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        //registerReceiver(pairDeviceReceiver, pairFilter);

        for(BluetoothDevice bluetoothDevice : bluetoothAdapter.getBondedDevices()){
            Device device = new Device();
            device.setDeviceName(bluetoothDevice.getName());
            device.setDeviceMacAddress(bluetoothDevice.getAddress());
            deviceList.add(device);
        }

        PairDeviceAdapter adapter = new PairDeviceAdapter(this, deviceList);
        pairRecyclerView.setHasFixedSize(true);
        pairRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pairRecyclerView.setAdapter(adapter);
    }

    /*BroadcastReceiver pairDeviceReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
                BluetoothDevice mDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (mDevice.getBondState() == BluetoothDevice.BOND_BONDED) {
                    bluetoothDevice = mDevice;
                }
            }
        }
    };*/
}
