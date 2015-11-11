package com.thewalkingdevs.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"id", "reference", "scope", "types",
        "vicinity", "rating", "opening_hours", "photos", "permanently_closed", "price_level"})
public class Place {

    @JsonProperty("geometry")
    private Geometry geometry = null;
    @JsonProperty("name")
    private String name = null;
    @JsonProperty("place_id")
    private String placeId = null;
    @JsonProperty("icon")
    private String icon = null;

    private float distFrom;

    private String description = null;

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

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDistFrom() {
        return distFrom;
    }

    public void setDistFrom(float distFrom) {
        this.distFrom = distFrom;
    }
}