package com.example.rk.uremotev2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rk.uremotev2.R;
import com.example.rk.uremotev2.activities.homescreen.HomeActivity;
import com.example.rk.uremotev2.classes.AppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApplianceRemoteFragment extends Fragment {

    @BindView(R.id.add_del_text)
    TextView addDeleteMainText;

    @BindView(R.id.up_down_text)
    TextView upDownMainText;

    private OnRemoteFragmentListener onRemoteFragmentListener;
    private String bundleString;

    public ApplianceRemoteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_remote, container, false);
        ButterKnife.bind(this, rootView);
        if (getArguments() != null) {
            bundleString = getArguments().getString(AppConstants.BUNDLE_STRING);
        }
        initFragment();
        return rootView;
    }


    void initFragment() {
        switch (bundleString) {

            case AppConstants.TELEVISION:
                addDeleteMainText.setText("Volume");
                upDownMainText.setText("Channel");
                break;

            case AppConstants.AIR_CONDITIONER:
                addDeleteMainText.setText("Temperature");
                upDownMainText.setText("Blow");
                break;

            case AppConstants.MUSIC_SYSTEM:
                addDeleteMainText.setText("Volume");
                upDownMainText.setText("Seek");
                break;

            case AppConstants.PROJECTOR:
                addDeleteMainText.setText("Zoom");
                upDownMainText.setText("Select");
                break;
        }
    }

    @OnClick(R.id.power_button)
    void OnClickPowerButton() {
        switch (bundleString) {

            case AppConstants.TELEVISION:
                onRemoteFragmentListener.onRemoteButtonClicked("6");
                break;

            case AppConstants.AIR_CONDITIONER:
                onRemoteFragmentListener.onRemoteButtonClicked("B");
                break;

            case AppConstants.MUSIC_SYSTEM:
                onRemoteFragmentListener.onRemoteButtonClicked("G");
                break;

            case AppConstants.PROJECTOR:
                onRemoteFragmentListener.onRemoteButtonClicked("1");
                break;
        }
    }

    @OnClick(R.id.volume_up)
    void OnClickVolumeUp() {


        if (HomeActivity.isWifiOrBluetooth.equals(AppConstants.WIFI)) {
            switch (bundleString) {
                case AppConstants.TELEVISION:
                    onRemoteFragmentListener.callSwitchOnOffApi("switch1", "0");
                    break;

                case AppConstants.AIR_CONDITIONER:
                    //onRemoteFragmentListener.callSwitchOnOffApi("switch1", "1");
                    break;

                case AppConstants.MUSIC_SYSTEM:
                    //onRemoteFragmentListener.callSwitchOnOffApi("switch1", "1");
                    break;

                case AppConstants.PROJECTOR:
                    //onRemoteFragmentListener.callSwitchOnOffApi("switch1", "1");
                    break;
            }
        } else {


            switch (bundleString) {

                case AppConstants.TELEVISION:
                    onRemoteFragmentListener.onRemoteButtonClicked("7");
                    break;

                case AppConstants.AIR_CONDITIONER:
                    onRemoteFragmentListener.onRemoteButtonClicked("C");
                    break;

                case AppConstants.MUSIC_SYSTEM:
                    onRemoteFragmentListener.onRemoteButtonClicked("H");
                    break;

                case AppConstants.PROJECTOR:
                    onRemoteFragmentListener.onRemoteButtonClicked("2");
                    break;
            }
        }
    }

    @OnClick(R.id.volume_down)
    void OnClickVolumeDown() {


        if (HomeActivity.isWifiOrBluetooth.equals(AppConstants.WIFI)) {
            switch (bundleString) {
                case AppConstants.TELEVISION:
                    onRemoteFragmentListener.callSwitchOnOffApi("switch1", "1");
                    break;

                case AppConstants.AIR_CONDITIONER:
                    //onRemoteFragmentListener.callSwitchOnOffApi("switch1", "1");
                    break;

                case AppConstants.MUSIC_SYSTEM:
                    //onRemoteFragmentListener.callSwitchOnOffApi("switch1", "1");
                    break;

                case AppConstants.PROJECTOR:
                    //onRemoteFragmentListener.callSwitchOnOffApi("switch1", "1");
                    break;
            }
        } else {

            switch (bundleString) {

                case AppConstants.TELEVISION:
                    onRemoteFragmentListener.onRemoteButtonClicked("8");
                    break;

                case AppConstants.AIR_CONDITIONER:
                    onRemoteFragmentListener.onRemoteButtonClicked("D");
                    break;

                case AppConstants.MUSIC_SYSTEM:
                    onRemoteFragmentListener.onRemoteButtonClicked("I");
                    break;

                case AppConstants.PROJECTOR:
                    onRemoteFragmentListener.onRemoteButtonClicked("3");
                    break;
            }
        }
    }

    @OnClick(R.id.channel_up)
    void OnClickChannelUp() {
        switch (bundleString) {

            case AppConstants.TELEVISION:
                onRemoteFragmentListener.onRemoteButtonClicked("9");
                break;

            case AppConstants.AIR_CONDITIONER:
                onRemoteFragmentListener.onRemoteButtonClicked("E");
                break;

            case AppConstants.MUSIC_SYSTEM:
                onRemoteFragmentListener.onRemoteButtonClicked("J");
                break;

            case AppConstants.PROJECTOR:
                onRemoteFragmentListener.onRemoteButtonClicked("4");
                break;
        }
    }

    @OnClick(R.id.channel_down)
    void OnClickChannelDown() {

        switch (bundleString) {
            case AppConstants.TELEVISION:
                onRemoteFragmentListener.onRemoteButtonClicked("A");
                break;

            case AppConstants.AIR_CONDITIONER:
                onRemoteFragmentListener.onRemoteButtonClicked("F");
                break;

            case AppConstants.MUSIC_SYSTEM:
                onRemoteFragmentListener.onRemoteButtonClicked("K");
                break;

            case AppConstants.PROJECTOR:
                onRemoteFragmentListener.onRemoteButtonClicked("5");
                break;
        }/**/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRemoteFragmentListener) {
            onRemoteFragmentListener = (OnRemoteFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() +
                    "must implement OnRemoteFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onRemoteFragmentListener = null;
    }

    public interface OnRemoteFragmentListener {
        void onRemoteButtonClicked(String data);

        void callSwitchOnOffApi(String switchType, String value);
    }
}
