package com.example.rk.uremotev2.activities.homescreen;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
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

    public static String isWifiOrBluetooth = "";

    private ApplianceListFragment applianceListFragment = new ApplianceListFragment();
    private ApplianceRemoteFragment applianceRemoteFragment = new ApplianceRemoteFragment();

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

        mPresenter.requestConnectionDialog();


        //mPresenter.enableDisableBluetooth();
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
    public void showConnectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Connection Type");
        builder.setMessage("You need to choose the connection type in order to connect to the remote device.");
        builder.setPositiveButton("WIFI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isWifiOrBluetooth = AppConstants.WIFI;
            }
        });

        builder.setNegativeButton("BLUETOOTH", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isWifiOrBluetooth = AppConstants.BLUETOOTH;
                mPresenter.enableDisableBluetooth();
            }
        });
        builder.create().show();
    }

    @Override
    public void onButtonClicked(String data) {
        mPresenter.sendData(data);
    }

    @Override
    public void onGridClicked(int position) {

        Bundle bundle  = new Bundle();

        switch (position) {
            case 0:
                setToolbarTitle("Television");
                bundle.putString(AppConstants.BUNDLE_STRING, AppConstants.TELEVISION);
                applianceRemoteFragment.setArguments(bundle);
                mPresenter.requestFragment(applianceRemoteFragment);
                break;
            case 1:
                setToolbarTitle("Air Conditioner");
                bundle.putString(AppConstants.BUNDLE_STRING, AppConstants.AIR_CONDITIONER);
                applianceRemoteFragment.setArguments(bundle);
                mPresenter.requestFragment(applianceRemoteFragment);
                break;
            case 2:
                setToolbarTitle("Music System");
                bundle.putString(AppConstants.BUNDLE_STRING, AppConstants.MUSIC_SYSTEM);
                applianceRemoteFragment.setArguments(bundle);
                mPresenter.requestFragment(applianceRemoteFragment);
                break;
            case 4:
                setToolbarTitle("Projector");
                bundle.putString(AppConstants.BUNDLE_STRING, AppConstants.PROJECTOR);
                applianceRemoteFragment.setArguments(bundle);
                mPresenter.requestFragment(applianceRemoteFragment);
                break;

            case 3:
                setToolbarTitle("Lights");
                bundle.putString(AppConstants.BUNDLE_STRING, AppConstants.LIGHTS);
                applianceListFragment.setArguments(bundle);
                mPresenter.requestFragment(applianceListFragment);
                break;
            case 5:
                setToolbarTitle("Fans");
                bundle.putString(AppConstants.BUNDLE_STRING, AppConstants.FANS);
                applianceListFragment.setArguments(bundle);
                mPresenter.requestFragment(applianceListFragment);
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

    @Override
    public void callSwitchOnOffApi(String switchType, String value) {
        mPresenter.callSwitchOnOffApiCall(switchType, value);
    }

    private void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
