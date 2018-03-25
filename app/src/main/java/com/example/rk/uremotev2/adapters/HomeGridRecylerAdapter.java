package com.example.rk.uremotev2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rk.uremotev2.R;
import com.example.rk.uremotev2.fragments.ApplianceGridFragment;

public class HomeGridRecylerAdapter extends RecyclerView.Adapter<HomeGridRecylerAdapter.HomeGridViewHolder> {

    private Context context;
    private int[] images;
    private String[] title;

    public HomeGridRecylerAdapter(Context context, int[] images, String[] title) {
        this.context = context;
        this.images = images;
        this.title = title;
    }

    @Override
    public HomeGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home, parent, false);
        return new HomeGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeGridViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class HomeGridViewHolder extends RecyclerView.ViewHolder {
        public HomeGridViewHolder(View itemView) {
            super(itemView);
        }
    }
}
