package com.thewalkingdevs.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data model for city indices.
 */
public class CityIndices {

    @JsonProperty("crime_index")
    private double crimeIndex;

    @JsonProperty("traffic_time_index")
    private double trafficTimeIndex;

    @JsonProperty("cpi_and_rent_index")
    private double cpiRentIndex;

    @JsonProperty("purchasing_power_incl_rent_index")
    private double purchasingPowerIndex;

    @JsonProperty("restaurant_price_index")
    private double restaurantPriceIndex;

    @JsonProperty("property_price_to_income_ratio")
    private double propertyPriceIndex;

    @JsonProperty("hotel_price_index")
    private double hotelPriceIndex;

    @JsonProperty("safety_index")
    private double safetyIndex;

    @JsonProperty("traffic_co2_index")
    private double trafficCarbonIndex;

    @JsonProperty("cpi_index")
    private double cpiIndex;

    @JsonProperty("traffic_inefficiency_index")
    private double trafficInefficiencyIndex;

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

    @JsonProperty("pollution_index")
    private double pollutionIndex;

    private String name;

    public double getCrimeIndex() {
        return crimeIndex;
    }

    public void setCrimeIndex(double crimeIndex) {
        this.crimeIndex = crimeIndex;
    }

    public double getTrafficTimeIndex() {
        return trafficTimeIndex;
    }

    public void setTrafficTimeIndex(double trafficTimeIndex) {
        this.trafficTimeIndex = trafficTimeIndex;
    }

    public double getCpiRentIndex() {
        return cpiRentIndex;
    }

    public void setCpiRentIndex(double cpiRentIndex) {
        this.cpiRentIndex = cpiRentIndex;
    }

    public double getPurchasingPowerIndex() {
        return purchasingPowerIndex;
    }

    public void setPurchasingPowerIndex(double purchasingPowerIndex) {
        this.purchasingPowerIndex = purchasingPowerIndex;
    }

    public double getRestaurantPriceIndex() {
        return restaurantPriceIndex;
    }

    public void setRestaurantPriceIndex(double restaurantPriceIndex) {
        this.restaurantPriceIndex = restaurantPriceIndex;
    }

    public double getPropertyPriceIndex() {
        return propertyPriceIndex;
    }

    public void setPropertyPriceIndex(double propertyPriceIndex) {
        this.propertyPriceIndex = propertyPriceIndex;
    }

    public double getHotelPriceIndex() {
        return hotelPriceIndex;
    }

    public void setHotelPriceIndex(double hotelPriceIndex) {
        this.hotelPriceIndex = hotelPriceIndex;
    }

    public double getSafetyIndex() {
        return safetyIndex;
    }

    public void setSafetyIndex(double safetyIndex) {
        this.safetyIndex = safetyIndex;
    }

    public double getTrafficCarbonIndex() {
        return trafficCarbonIndex;
    }

    public void setTrafficCarbonIndex(double trafficCarbonIndex) {
        this.trafficCarbonIndex = trafficCarbonIndex;
    }

    public double getCpiIndex() {
        return cpiIndex;
    }

    public void setCpiIndex(double cpiIndex) {
        this.cpiIndex = cpiIndex;
    }

    public double getTrafficInefficiencyIndex() {
        return trafficInefficiencyIndex;
    }

    public void setTrafficInefficiencyIndex(double trafficInefficiencyIndex) {
        this.trafficInefficiencyIndex = trafficInefficiencyIndex;
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

    public double getPollutionIndex() {
        return pollutionIndex;
    }

    public void setPollutionIndex(double pollutionIndex) {
        this.pollutionIndex = pollutionIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
