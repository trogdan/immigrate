package com.thewalkingdevs.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Geometry {

    @JsonProperty("location")
    private Location location = null;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
