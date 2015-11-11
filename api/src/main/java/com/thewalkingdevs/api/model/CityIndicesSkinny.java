package com.thewalkingdevs.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data model for city indices.
 */
public class CityIndicesSkinny {

    @JsonProperty("crime_index")
    private double crimeIndex;

    @JsonProperty("property_price_to_income_ratio")
    private double propertyPriceIndex;

    @JsonProperty("quality_of_life_index")
    private double qualityOfLifeIndex;

    @JsonProperty("rent_index")
    private double rentIndex;

    @JsonProperty("health_care_index")
    private double healthcareIndex;

    @JsonProperty("traffic_index")
    private double trafficIndex;

    @JsonProperty("groceries_index")
    private double groceriesIndex;

    public double getCrimeIndex() {
        return crimeIndex;
    }

    public void setCrimeIndex(double crimeIndex) {
        this.crimeIndex = crimeIndex;
    }

    public double getPropertyPriceIndex() {
        return propertyPriceIndex;
    }

    public void setPropertyPriceIndex(double propertyPriceIndex) {
        this.propertyPriceIndex = propertyPriceIndex;
    }

    public double getQualityOfLifeIndex() {
        return qualityOfLifeIndex;
    }

    public void setQualityOfLifeIndex(double qualityOfLifeIndex) {
        this.qualityOfLifeIndex = qualityOfLifeIndex;
    }

    public double getRentIndex() {
        return rentIndex;
    }

    public void setRentIndex(double rentIndex) {
        this.rentIndex = rentIndex;
    }

    public double getHealthcareIndex() {
        return healthcareIndex;
    }

    public void setHealthcareIndex(double healthcareIndex) {
        this.healthcareIndex = healthcareIndex;
    }

    public double getTrafficIndex() {
        return trafficIndex;
    }

    public void setTrafficIndex(double trafficIndex) {
        this.trafficIndex = trafficIndex;
    }

    public double getGroceriesIndex() {
        return groceriesIndex;
    }

    public void setGroceriesIndex(double groceriesIndex) {
        this.groceriesIndex = groceriesIndex;
    }
}
