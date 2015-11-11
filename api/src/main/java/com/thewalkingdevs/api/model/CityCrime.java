package com.thewalkingdevs.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data model representing the city crime info from numbeo.
 */
@JsonIgnoreProperties({"worried_attacked", "worried_things_car_stolen", "worried_insulted", "monthLastUpdate",
        "contributors", "yearLastUpdate", "worried_mugged_robbed", "city_id"})
public class CityCrime {
    @JsonProperty("level_of_crime")
    private double crimeLevel;
    @JsonProperty("problem_property_crimes")
    private double propertyCrimeIndex;
    @JsonProperty("safe_alone_night")
    private double nightSafetyIndex;
    @JsonProperty("worried_skin_ethnic_religion")
    private double diversityThreatIndex;
    @JsonProperty("index_safety")
    private double safetyIndex;
    @JsonProperty("worried_car_stolen")
    private double carTheftWorryIndex;
    @JsonProperty("worried_home_broken")
    private double homeBreakinsWorryIndex;
    @JsonProperty("crime_increasing")
    private double crimeIncreasingIndex;
    @JsonProperty("problem_corruption_bribery")
    private double corruptionIndex;
    @JsonProperty("safe_alone_daylight")
    private double daylightSafetyIndex;
    @JsonProperty("problem_drugs")
    private double drugCrimeIndex;
    @JsonProperty("index_crime")
    private double crimeIndex;
    @JsonProperty("problem_violent_crimes")
    private double violentCrimeIndex;
    private String name;

    public double getCrimeLevel() {
        return crimeLevel;
    }

    public void setCrimeLevel(double crimeLevel) {
        this.crimeLevel = crimeLevel;
    }

    public double getPropertyCrimeIndex() {
        return propertyCrimeIndex;
    }

    public void setPropertyCrimeIndex(double propertyCrimeIndex) {
        this.propertyCrimeIndex = propertyCrimeIndex;
    }

    public double getNightSafetyIndex() {
        return nightSafetyIndex;
    }

    public void setNightSafetyIndex(double nightSafetyIndex) {
        this.nightSafetyIndex = nightSafetyIndex;
    }

    public double getDiversityThreatIndex() {
        return diversityThreatIndex;
    }

    public void setDiversityThreatIndex(double diversityThreatIndex) {
        this.diversityThreatIndex = diversityThreatIndex;
    }

    public double getSafetyIndex() {
        return safetyIndex;
    }

    public void setSafetyIndex(double safetyIndex) {
        this.safetyIndex = safetyIndex;
    }

    public double getCarTheftWorryIndex() {
        return carTheftWorryIndex;
    }

    public void setCarTheftWorryIndex(double carTheftWorryIndex) {
        this.carTheftWorryIndex = carTheftWorryIndex;
    }

    public double getHomeBreakinsWorryIndex() {
        return homeBreakinsWorryIndex;
    }

    public void setHomeBreakinsWorryIndex(double homeBreakinsWorryIndex) {
        this.homeBreakinsWorryIndex = homeBreakinsWorryIndex;
    }

    public double getCrimeIncreasingIndex() {
        return crimeIncreasingIndex;
    }

    public void setCrimeIncreasingIndex(double crimeIncreasingIndex) {
        this.crimeIncreasingIndex = crimeIncreasingIndex;
    }

    public double getCorruptionIndex() {
        return corruptionIndex;
    }

    public void setCorruptionIndex(double corruptionIndex) {
        this.corruptionIndex = corruptionIndex;
    }

    public double getDaylightSafetyIndex() {
        return daylightSafetyIndex;
    }

    public void setDaylightSafetyIndex(double daylightSafetyIndex) {
        this.daylightSafetyIndex = daylightSafetyIndex;
    }

    public double getDrugCrimeIndex() {
        return drugCrimeIndex;
    }

    public void setDrugCrimeIndex(double drugCrimeIndex) {
        this.drugCrimeIndex = drugCrimeIndex;
    }

    public double getCrimeIndex() {
        return crimeIndex;
    }

    public void setCrimeIndex(double crimeIndex) {
        this.crimeIndex = crimeIndex;
    }

    public double getViolentCrimeIndex() {
        return violentCrimeIndex;
    }

    public void setViolentCrimeIndex(double violentCrimeIndex) {
        this.violentCrimeIndex = violentCrimeIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
