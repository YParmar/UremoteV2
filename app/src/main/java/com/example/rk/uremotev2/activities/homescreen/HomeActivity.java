package com.example.rk.uremotev2.activities.homescreen;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.example.rk.uremotev2.R;
import com.example.rk.uremotev2.activities.pairscreen.PairActivity;
import com.example.rk.uremotev2.base.BaseActivity;
import com.example.rk.uremotev2.classes.AppConstants;
import com.example.rk.uremotev2.fragments.ApplianceGridFragment;
import com.example.rk.uremotev2.fragments.ApplianceListFragment;
import com.example.rk.uremotev2.fragments.ApplianceRemoteFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity<HomeActivityPresenter> implements HomeActivityContract.HomeView,
        ApplianceGridFragment.OnSelectAppliancesFragmentListener, ApplianceListFragment.OnApplianceRemoteFragmentListener,
        ApplianceRemoteFragment.OnRemoteFragmentListener {

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
    public void onGridClicked(int position) {

        switch (position) {
            case 0:
                setToolbarTitle("Television");
                mPresenter.requestFragment(new ApplianceRemoteFragment());
                break;
            case 1:
                setToolbarTitle("Air Conditioner");
                mPresenter.requestFragment(new ApplianceRemoteFragment());
                break;
            case 2:
                setToolbarTitle("Music System");
                mPresenter.requestFragment(new ApplianceRemoteFragment());
                break;
            case 4:
                setToolbarTitle("Projector");
                mPresenter.requestFragment(new ApplianceRemoteFragment());
                break;

            case 3:
                setToolbarTitle("Lights");
                mPresenter.requestFragment(new ApplianceListFragment());
                break;
            case 5:
                setToolbarTitle("Fans");
                mPresenter.requestFragment(new ApplianceListFragment());
                break;
        }

    }

    @Override
    public void onBackPressed() {
        Fragment currentLoadedFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (currentLoadedFragment instanceof ApplianceListFragment || currentLoadedFragment instanceof ApplianceRemoteFragment) {
            setToolbarTitle("Home");
            mPresenter.requestFragment(new ApplianceGridFragment());
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRemoteButtonClicked(String data) {
        mPresenter.sendData(data);
        showMessage(data);
    }

    private void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
