package com.thewalkingdevs.api.model;

import java.util.List;

/**
 * Data model for city info.
 */
public class CityPrices {

    String name;
    String currency;
    int monthLastUpdate;
    int contributors;
    int yearLastUpdate;
    List<ItemPrice> prices;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getMonthLastUpdate() {
        return monthLastUpdate;
    }

    public void setMonthLastUpdate(int monthLastUpdate) {
        this.monthLastUpdate = monthLastUpdate;
    }

    public int getContributors() {
        return contributors;
    }

    public void setContributors(int contributors) {
        this.contributors = contributors;
    }

    public int getYearLastUpdate() {
        return yearLastUpdate;
    }

    public void setYearsLastUpdate(int yearsLastUpdate) {
        this.yearLastUpdate = yearsLastUpdate;
    }

    public List<ItemPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<ItemPrice> prices) {
        this.prices = prices;
    }
}
