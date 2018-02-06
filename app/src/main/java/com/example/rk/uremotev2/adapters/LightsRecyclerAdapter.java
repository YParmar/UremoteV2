package com.example.rk.uremotev2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.rk.uremotev2.R;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LightsRecyclerAdapter extends RecyclerView.Adapter<LightsRecyclerAdapter.LightsViewHolder> {

    private List<String> lightsName;
    private Context context;
    private OnLightsAdapterListener onLightsAdapterListener;

    public LightsRecyclerAdapter(Context context, List<String> lightsName, OnLightsAdapterListener onLightsAdapterListener) {
        this.context = context;
        this.lightsName = lightsName;
        this.onLightsAdapterListener = onLightsAdapterListener;
    }

    @Override
    public LightsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LightsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.light_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(LightsViewHolder holder, int position) {
        holder.bindViews(lightsName.get(position));
    }

    @Override
    public int getItemCount() {
        return lightsName.size();
    }

    public class LightsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.light_name)
        TextView lightsName;
        @BindView(R.id.light_icon)
        ImageView lightIcon;

        public LightsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindViews(String name) {
            lightsName.setText(name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onLightsAdapterListener != null) {
                        onLightsAdapterListener.onPowerButtonClicked(String.valueOf(getAdapterPosition() + 1));
                    }
                }
            });
        }

    }

    public interface OnLightsAdapterListener {
        void onPowerButtonClicked(String data);
    }
}
