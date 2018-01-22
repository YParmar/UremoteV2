package com.example.rk.uremotev2.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rk.uremotev2.R;
import com.example.rk.uremotev2.adapters.LightsRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApplianceRemoteFragment extends Fragment implements LightsRecyclerAdapter.OnLightsAdapterListener{

    @BindView(R.id.lights_recyclerview)
    RecyclerView lightsRecyclerView;

    private OnApplianceRemoteFragmentListener mListener;

    public ApplianceRemoteFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_appliance_remote, container, false);
        ButterKnife.bind(this, rootView);
        initFragment();
        return rootView;
    }

    void initFragment(){
        List<String> lightsNames = new ArrayList<>();
        lightsNames.add("Main Light Upstairs");
        lightsNames.add("Light Upstairs");
        lightsNames.add("Light Downstairs");
        lightsNames.add("Fan");

        LightsRecyclerAdapter lightsRecyclerAdapter = new LightsRecyclerAdapter(getContext(), lightsNames, this);
        lightsRecyclerView.setHasFixedSize(true);
        lightsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        lightsRecyclerView.setAdapter(lightsRecyclerAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnApplianceRemoteFragmentListener){
            mListener = (OnApplianceRemoteFragmentListener) context;
        }else {
            throw new RuntimeException(context.toString()
                    + "must implement OnApplianceRemoteFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPowerButtonClicked(String data) {
        mListener.onButtonClicked(data);
    }

    public interface OnApplianceRemoteFragmentListener{
        void onButtonClicked(String data);
    }

}
