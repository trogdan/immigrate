package com.thewalkingdevs.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data model for representing city healthcare information.
 */
public class CityHealthCareInfoSkinny {

    @JsonProperty("skill_and_competency")
    private double healthCareStaffCompetency;
    @JsonProperty("cost")
    private double healthCareCost;
    @JsonProperty("responsiveness_waitings")
    private double responsivenessIndex;
    @JsonProperty("speed")
    private double speedIndex;
    @JsonProperty("accuracy_and_completeness")
    private double accuracyIndex;
    @JsonProperty("friendliness_and_courtesy")
    private double friendlinessIndex;
    @JsonProperty("modern_equipment")
    private int modernEquipmentIndex;

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

    public int getModernEquipmentIndex() {
        return modernEquipmentIndex;
    }

    public void setModernEquipmentIndex(int modernEquipmentIndex) {
        this.modernEquipmentIndex = modernEquipmentIndex;
    }

}
