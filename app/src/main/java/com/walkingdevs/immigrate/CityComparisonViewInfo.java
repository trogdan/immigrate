package com.walkingdevs.immigrate;

import com.thewalkingdevs.api.myApi.model.CityBag;

/**
 * Data model for representing a city prices comparison view info.
 */
public class CityComparisonViewInfo {
    private String firstCity;
    private String secondCity;

    private CityBag firstCityBag;
    private CityBag secondCityBag;

    public String getFirstCity() {
        return firstCity;
    }

    public void setFirstCity(String firstCity) {
        this.firstCity = firstCity;
    }

    public String getSecondCity() {
        return secondCity;
    }

    public void setSecondCity(String secondCity) {
        this.secondCity = secondCity;
    }

    public CityBag getFirstCityBag() {
        return firstCityBag;
    }

    public void setFirstCityBag(CityBag firstCityBag) {
        this.firstCityBag = firstCityBag;
    }

    public CityBag getSecondCityBag() {
        return secondCityBag;
    }

    public void setSecondCityBag(CityBag secondCityBag) {
        this.secondCityBag = secondCityBag;
    }
}
