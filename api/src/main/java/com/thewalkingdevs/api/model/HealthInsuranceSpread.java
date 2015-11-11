package com.thewalkingdevs.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data model for representing health care funding in percentages.
 */
@JsonIgnoreProperties({"None"})
public class HealthInsuranceSpread {
    @JsonProperty("Employer Sponsored")
    private double employerSponsoredPercentage;
    @JsonProperty("Private")
    private double privateFundedPercentage;
    @JsonProperty("Public")
    private double publicFundedPercentage;

    public double getEmployerSponsoredPercentage() {
        return employerSponsoredPercentage;
    }

    public void setEmployerSponsoredPercentage(double employerSponsoredPercentage) {
        this.employerSponsoredPercentage = employerSponsoredPercentage;
    }

    public double getPrivateFundedPercentage() {
        return privateFundedPercentage;
    }

    public void setPrivateFundedPercentage(double privateFundedPercentage) {
        this.privateFundedPercentage = privateFundedPercentage;
    }

    public double getPublicFundedPercentage() {
        return publicFundedPercentage;
    }

    public void setPublicFundedPercentage(double publicFundedPercentage) {
        this.publicFundedPercentage = publicFundedPercentage;
    }
}
