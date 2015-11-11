package com.thewalkingdevs.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data model for representing city healthCare information.
 */
@JsonIgnoreProperties({"monthLastUpdate", "contributors", "yearLastUpdate", "city_id"})
public class CityHealthCareInfo {

    @JsonProperty("skill_and_competency")
    private double healthCareStaffCompetency;
    @JsonProperty("cost")
    private double healthCareCost;
    @JsonProperty("responsiveness_waitings")
    private double responsivenessIndex;git
    @JsonProperty("index_healthcare")
    private double healthCareIndex;
    @JsonProperty("speed")
    private double speedIndex;
    @JsonProperty("accuracy_and_completeness")
    private double accuracyIndex;
    @JsonProperty("friendliness_and_courtesy")
    private double friendlinessIndex;
    @JsonProperty("insurance_type")
    private HealthInsuranceSpread insuranceSpread;
    @JsonProperty("modern_equipment")
    private int modernEquipmentIndex;
    @JsonProperty("location")
    private double locationIndex;
    private String name;

    public double getHealthCareStaffCompetency() {
        return healthCareStaffCompetency;
    }

    public void setHealthCareStaffCompetency(double healthCareStaffCompetency) {
        this.healthCareStaffCompetency = healthCareStaffCompetency;
    }

    public double getHealthCareCost() {
        return healthCareCost;
    }

    public void setHealthCareCost(double healthCareCost) {
        this.healthCareCost = healthCareCost;
    }

    public double getResponsivenessIndex() {
        return responsivenessIndex;
    }

    public void setResponsivenessIndex(double responsivenessIndex) {
        this.responsivenessIndex = responsivenessIndex;
    }

    public double getHealthCareIndex() {
        return healthCareIndex;
    }

    public void setHealthCareIndex(double healthCareIndex) {
        this.healthCareIndex = healthCareIndex;
    }

    public double getSpeedIndex() {
        return speedIndex;
    }

    public void setSpeedIndex(double speedIndex) {
        this.speedIndex = speedIndex;
    }

    public double getAccuracyIndex() {
        return accuracyIndex;
    }

    public void setAccuracyIndex(double accuracyIndex) {
        this.accuracyIndex = accuracyIndex;
    }

    public double getFriendlinessIndex() {
        return friendlinessIndex;
    }

    public void setFriendlinessIndex(double friendlinessIndex) {
        this.friendlinessIndex = friendlinessIndex;
    }

    public HealthInsuranceSpread getInsuranceSpread() {
        return insuranceSpread;
    }

    public void setInsuranceSpread(HealthInsuranceSpread insuranceSpread) {
        this.insuranceSpread = insuranceSpread;
    }

    public int getModernEquipmentIndex() {
        return modernEquipmentIndex;
    }

    public void setModernEquipmentIndex(int modernEquipmentIndex) {
        this.modernEquipmentIndex = modernEquipmentIndex;
    }

    public double getLocationIndex() {
        return locationIndex;
    }

    public void setLocationIndex(double locationIndex) {
        this.locationIndex = locationIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
