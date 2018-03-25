package com.example.rk.uremotev2.model;

import com.google.gson.annotations.SerializedName;

public class FeedSwitchResponse {

    /*{
        "id": "0DTY03MSFCSZX80CYKMSGJEMZ4",
            "value": "0",
            "feed_id": 756419,
            "feed_key": "switch2",
            "created_at": "2018-03-24T20:07:24Z",
            "location": null,
            "lat": null,
            "lon": null,
            "ele": null,
            "created_epoch": 1521922044,
            "expiration": "2018-04-23T20:07:24Z"
    }*/

    @SerializedName("id")
    private String id;

    @SerializedName("value")
    private String value;

    @SerializedName("feed_id")
    private String feedId;

    @SerializedName("created_at")
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
