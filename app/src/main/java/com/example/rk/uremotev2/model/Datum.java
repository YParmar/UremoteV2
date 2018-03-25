package com.example.rk.uremotev2.model;

import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("value")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
