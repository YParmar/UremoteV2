package com.example.rk.uremotev2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.bumptech.glide.Glide;
import com.example.rk.uremotev2.R;
import com.example.rk.uremotev2.adapters.ApplianceGridAdapter;
import com.example.rk.uremotev2.adapters.HomeGridRecylerAdapter;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class ApplianceGridFragment extends Fragment implements HomeGridRecylerAdapter.HomeGridRecyclerListener {

    @BindView(R.id.home_grid_recyclerview)
    RecyclerView homeGridRecyclerview;

    /*@BindView(R.id.appliance_grid_view)
    GridView applianceGridView;*/

    private int[] applianceIcons = {R.drawable.television_red,
            R.drawable.air_conditioner,
            R.drawable.music,
            R.drawable.bulb,
            R.drawable.projector,
            R.drawable.fan
    };

    private OnSelectAppliancesFragmentListener mListener;

    public ApplianceGridFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_appliance_grid, container, false);
        ButterKnife.bind(this, rootView);
        initFragment();
        return rootView;
    }

    void initFragment(){

        HomeGridRecylerAdapter adapter = new HomeGridRecylerAdapter(getContext(), applianceIcons,
                getResources().getStringArray(R.array.appliance_names), Glide.with(this), this);
        homeGridRecyclerview.setHasFixedSize(true);
        homeGridRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));
        homeGridRecyclerview.setAdapter(adapter);

        /*ApplianceGridAdapter applianceGridAdapter = new ApplianceGridAdapter(getContext(),
                getResources().getStringArray(R.array.appliance_names), applianceIcons);
        applianceGridView.setAdapter(applianceGridAdapter);*/
    }

    /*@OnItemClick(R.id.appliance_grid_view)
    void onGridItemClicked(int position){
        mListener.onGridClicked(position);
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSelectAppliancesFragmentListener) {
            mListener = (OnSelectAppliancesFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onGridClicked(int position) {
        mListener.onGridClicked(position);
    }

    public interface OnSelectAppliancesFragmentListener {
        void onGridClicked(int position);
    }
}
