package com.thewalkingdevs.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data model representing the city crime info from numbeo.
 */
public class  CityCrimeSkinny {
    @JsonProperty("level_of_crime")
    private double crimeLevel;
    @JsonProperty("safe_alone_night")
    private double nightSafetyIndex;
    @JsonProperty("worried_skin_ethnic_religion")
    private double diversityThreatIndex;
    @JsonProperty("problem_corruption_bribery")
    private double corruptionIndex;
    @JsonProperty("safe_alone_daylight")
    private double daylightSafetyIndex;
    @JsonProperty("problem_drugs")
    private double drugCrimeIndex;
    @JsonProperty("problem_violent_crimes")
    private double violentCrimeIndex;

    public double getCrimeLevel() {
        return crimeLevel;
    }

    public void setCrimeLevel(double crimeLevel) {
        this.crimeLevel = crimeLevel;
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

    public double getViolentCrimeIndex() {
        return violentCrimeIndex;
    }

    public void setViolentCrimeIndex(double violentCrimeIndex) {
        this.violentCrimeIndex = violentCrimeIndex;
    }

}
