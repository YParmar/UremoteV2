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

import com.example.rk.uremotev2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApplianceRemoteFragment extends Fragment {

    private OnRemoteFragmentListener onRemoteFragmentListener;

    public ApplianceRemoteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_remote, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.power_button)
    void OnClickPowerButton() {
        onRemoteFragmentListener.onRemoteButtonClicked("1");
    }

    @OnClick(R.id.volume_up)
    void OnClickVolumeUp() {
        onRemoteFragmentListener.onRemoteButtonClicked("2");
    }

    @OnClick(R.id.volume_down)
    void OnClickVolumeDown() {
        onRemoteFragmentListener.onRemoteButtonClicked("3");
    }

    @OnClick(R.id.channel_up)
    void OnClickChannelUp() {
        onRemoteFragmentListener.onRemoteButtonClicked("4");
    }

    @OnClick(R.id.channel_down)
    void OnClickChannelDown() {
        onRemoteFragmentListener.onRemoteButtonClicked("5");
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
    }

}
