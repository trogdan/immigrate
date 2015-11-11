package com.thewalkingdevs.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"place_id", "reference", "scope", "types",
        "vicinity", "rating", "opening_hours", "photos", "permanently_closed"})
public class Place {

    @JsonProperty("geometry")
    private Geometry geometry = null;
    @JsonProperty("name")
    private String name = null;
    @JsonProperty("id")
    private String id = null;
    @JsonProperty("icon")
    private String icon = null;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
