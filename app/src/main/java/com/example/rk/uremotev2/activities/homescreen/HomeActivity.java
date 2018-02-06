package com.example.rk.uremotev2.activities.homescreen;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.rk.uremotev2.R;
import com.example.rk.uremotev2.activities.pairscreen.PairActivity;
import com.example.rk.uremotev2.base.BaseActivity;
import com.example.rk.uremotev2.classes.AppConstants;
import com.example.rk.uremotev2.fragments.ApplianceGridFragment;
import com.example.rk.uremotev2.fragments.ApplianceRemoteFragment;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity<HomeActivityPresenter> implements HomeActivityContract.HomeView,
        ApplianceGridFragment.OnSelectAppliancesFragmentListener, ApplianceRemoteFragment.OnApplianceRemoteFragmentListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private BluetoothAdapter bluetoothAdapter;
    public static final int BT_DEVICE_RESULT_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPresenter = new HomeActivityPresenter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initActionBar(toolbar, true, "Home");
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mPresenter.requestFragment(new ApplianceGridFragment());
        mPresenter.enableDisableBluetooth();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unregisterBluetoothBroadCastReceiver();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == BT_DEVICE_RESULT_CODE) {
            BluetoothDevice device = bluetoothAdapter.getRemoteDevice(data.getStringExtra(AppConstants.MAC_ADDRESS));
            mPresenter.startConnection(device, AppConstants.MY_UUID_INSECURE);
        }
    }

    @Override
    public void startPairActivityForResult() {
        Intent pairIntent = new Intent(this, PairActivity.class);
        startActivityForResult(pairIntent, BT_DEVICE_RESULT_CODE);
    }

    @Override
    public void setRequestedFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void onButtonClicked(String data) {
        mPresenter.sendData(data);
    }

    @Override
    public void onGridClicked() {
        mPresenter.requestFragment(new ApplianceRemoteFragment());
    }

    @Override
    public void onBackPressed() {
        Fragment currentLoadedFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (currentLoadedFragment instanceof ApplianceRemoteFragment) {
            mPresenter.requestFragment(new ApplianceGridFragment());
        } else {
            super.onBackPressed();
        }
    }
}
