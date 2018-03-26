package com.example.rk.uremotev2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.example.rk.uremotev2.R;
import com.example.rk.uremotev2.fragments.ApplianceGridFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeGridRecylerAdapter extends RecyclerView.Adapter<HomeGridRecylerAdapter.HomeGridViewHolder> {

    private Context context;
    private int[] images;
    private String[] title;
    private RequestManager glide;

    public HomeGridRecylerAdapter(Context context, int[] images, String[] title, RequestManager glide) {
        this.context = context;
        this.images = images;
        this.title = title;
        this.glide = glide;
    }

    @Override
    public HomeGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_grid_layout, parent, false);
        return new HomeGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeGridViewHolder holder, int position) {
        holder.bindItems(position);
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class HomeGridViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.home_grid_image)
        ImageView homeGridImage;
        @BindView(R.id.home_grid_text)
        TextView homeGridText;

        public HomeGridViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void bindItems(int position) {
            glide.load(images[position])
                    .crossFade()
                    .into(homeGridImage);

            homeGridText.setText(title[position]);
        }
    }
}
