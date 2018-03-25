package com.example.rk.uremotev2.model;

import com.google.gson.annotations.SerializedName;

public class FeedSwitchRequest {

    @SerializedName("datum")
    private Datum datum;

    public Datum getDatum() {
        return datum;
    }

    public void setDatum(Datum datum) {
        this.datum = datum;
    }
}
