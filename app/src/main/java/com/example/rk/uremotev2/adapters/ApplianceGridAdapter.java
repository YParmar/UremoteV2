package com.example.rk.uremotev2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rk.uremotev2.R;

public class ApplianceGridAdapter extends BaseAdapter {

    private String[] applianceNames;
    private int[] applianceIcons;
    private Context context;

    public ApplianceGridAdapter(Context context, String[] applianceNames, int[] applianceIcons) {
        this.context = context;
        this.applianceNames = applianceNames;
        this.applianceIcons = applianceIcons;
    }

    @Override
    public int getCount() {
        return applianceNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        ImageView gridImage;
        TextView gridText;

        if (convertView == null) {
            gridView = inflater.inflate(R.layout.grid_layout, null);
            gridImage = (ImageView) gridView.findViewById(R.id.gridimg);
            gridText = (TextView) gridView.findViewById(R.id.gridtxt);
            gridText.setText(applianceNames[position]);
            Glide.with(context).load(applianceIcons[position]).into(gridImage);

        } else {
            gridView = convertView;
        }

        return gridView;
    }
}
