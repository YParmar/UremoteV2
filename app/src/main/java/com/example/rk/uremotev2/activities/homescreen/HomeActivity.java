package com.example.rk.uremotev2.activities.homescreen;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.rk.uremotev2.R;
import com.example.rk.uremotev2.activities.pairscreen.PairActivity;
import com.example.rk.uremotev2.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity<HomeActivityPresenter> implements HomeActivityContract.HomeView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.enable)
    Button enable;

    private GridView gridView;
    public static final String POSITION = "position";
    public static final String TOOLBAR_TITLE = "toolbar_title";
    //private PairDeviceAdapter applianceAdapter;
    private BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPresenter = new HomeActivityPresenter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initActionBar(toolbar, true, "Home");
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableDisableBluetooth();
            }
        });

        /*applianceAdapter = new PairDeviceAdapter(this, applianceNames, applianceImages);
        gridView = (GridView) findViewById(R.id.appliance_view);
        gridView.setAdapter(applianceAdapter);
        gridView.setOnItemClickListener(this);*/
    }

    private int[] applianceImages = {R.drawable.television_red,
            R.drawable.air_conditioner,
            R.drawable.speaker,
            R.drawable.chandelier,
            R.drawable.projector
    };

    private String[] applianceNames = {"Television", "Air Conditioner", "Music System", "Lights", "Projector"};


    /*@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, TVRemoteActivity.class);
        intent.putExtra(POSITION, position);
        intent.putExtra(TOOLBAR_TITLE, applianceNames[position]);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                onBackPressed();
                break;
        }

        return true;
    }*/

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(bluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, bluetoothAdapter.ERROR);

                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        showMessage("Bluetooth turned off");
                        break;

                    case BluetoothAdapter.STATE_ON:
                        showMessage("Bluetooth turned on");
                        Intent pairedIntent = new Intent(HomeActivity.this, PairActivity.class);
                        startActivityForResult(pairedIntent, 100);
                        break;

                }
            }
        }
    };


    private void enableDisableBluetooth() {
        if (bluetoothAdapter == null) {
            showMessage("Your device does not have bluetooth capabilities");
        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableIntent);
            IntentFilter BtIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(broadcastReceiver, BtIntent);
        }
        if (bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
            IntentFilter BtIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(broadcastReceiver, BtIntent);
        }

    }

}
