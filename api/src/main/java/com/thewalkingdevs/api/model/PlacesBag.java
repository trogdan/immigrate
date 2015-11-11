package com.thewalkingdevs.api.model;

public class PlacesBag {

    Places services = null;
    Places transportation = null;
    Places essentials = null;

    public Places getServices() {
        return services;
    }

    public void setServices(Places services) {
        this.services = services;
    }

    public Places getTransportation() {
        return transportation;
    }

    public void setTransportation(Places transportation) {
        this.transportation = transportation;
    }

    public Places getEssentials() {
        return essentials;
    }

    public void setEssentials(Places essentials) {
        this.essentials = essentials;
    }


}
