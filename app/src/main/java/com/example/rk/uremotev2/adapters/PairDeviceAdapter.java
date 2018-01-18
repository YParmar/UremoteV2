package com.example.rk.uremotev2.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rk.uremotev2.Model.Device;
import com.example.rk.uremotev2.R;
import com.example.rk.uremotev2.activities.pairscreen.PairActivity;
import com.example.rk.uremotev2.classes.AppConstants;
import com.example.rk.uremotev2.services.BluetoothSendService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PairDeviceAdapter extends RecyclerView.Adapter<PairDeviceAdapter.PairViewHolder>{

    private Context context;
    private List<Device> deviceList;

    public PairDeviceAdapter(Context context, List<Device> deviceList) {
        this.context = context;
        this.deviceList = deviceList;
    }

    @Override
    public PairViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PairViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pair_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(PairViewHolder holder, int position) {
        holder.bindViews(deviceList.get(position));
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public class PairViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.device_name)
        TextView deviceName;
        @BindView(R.id.device_address)
        TextView deviceAddress;

        public PairViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        private void bindViews(Device device){
            deviceName.setText(device.getDeviceName());
            deviceAddress.setText(device.getDeviceMacAddress());
        }

        @Override
        public void onClick(View v) {
            Intent bluetoothServiceIntent = new Intent(context, BluetoothSendService.class);
            bluetoothServiceIntent.putExtra(AppConstants.BT_DEVICE, deviceList.get(getAdapterPosition()));
            context.startService(bluetoothServiceIntent);
        }
    }
}
