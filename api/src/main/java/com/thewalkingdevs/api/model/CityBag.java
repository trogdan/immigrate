package com.thewalkingdevs.api.model;

/**
 * Data model for representing all the needed city data.
 */
public class CityBag {
    private CityPricesSkinny cityPrices;
    private CityIndicesSkinny cityIndices;
    private CityHealthCareInfoSkinny cityHealthCareInfo;
    private CityCrimeSkinny cityCrime;

    public CityPricesSkinny getCityPrices() {
        return cityPrices;
    }

    public void setCityPrices(CityPricesSkinny cityPrices) {
        this.cityPrices = cityPrices;
    }

    public CityIndicesSkinny getCityIndices() {
        return cityIndices;
    }

    public void setCityIndices(CityIndicesSkinny cityIndices) {
        this.cityIndices = cityIndices;
    }

    public CityHealthCareInfoSkinny getCityHealthCareInfo() {
        return cityHealthCareInfo;
    }

    public void setCityHealthCareInfo(CityHealthCareInfoSkinny cityHealthCareInfo) {
        this.cityHealthCareInfo = cityHealthCareInfo;
    }

    public CityCrimeSkinny getCityCrime() {
        return cityCrime;
    }

    public void setCityCrime(CityCrimeSkinny cityCrime) {
        this.cityCrime = cityCrime;
    }
}
