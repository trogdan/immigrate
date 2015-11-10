package com.thewalkingdevs.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by levani on 11/10/15.
 */
public class Location {

    @JsonProperty("lat")
    private float lat;
    @JsonProperty("lng")
    private float lng;

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }
}
