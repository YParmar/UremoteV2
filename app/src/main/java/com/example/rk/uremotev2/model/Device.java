package com.example.rk.uremotev2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Device implements Parcelable{

    private String deviceName;
    private String deviceMacAddress;

    protected Device(Parcel in) {
        deviceName = in.readString();
        deviceMacAddress = in.readString();
    }

    public Device(){}

    public static final Creator<Device> CREATOR = new Creator<Device>() {
        @Override
        public Device createFromParcel(Parcel in) {
            return new Device(in);
        }

        @Override
        public Device[] newArray(int size) {
            return new Device[size];
        }
    };

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceMacAddress() {
        return deviceMacAddress;
    }

    public void setDeviceMacAddress(String deviceMacAddress) {
        this.deviceMacAddress = deviceMacAddress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(deviceName);
        dest.writeString(deviceMacAddress);
    }
}
