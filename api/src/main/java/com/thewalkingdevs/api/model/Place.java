package com.thewalkingdevs.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"icon", "id", "place_id", "reference", "scope", "types", "vicinity"})
public class Place {

    @JsonProperty("geometry")
    private Geometry geometry = null;
    @JsonProperty("name")
    private String name = null;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
