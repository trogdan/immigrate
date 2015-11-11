package com.thewalkingdevs.api.model;

import java.util.List;

/**
 * Data model for city info.
 */
public class CityPricesSkinny {
    String currency;
    List<ItemPriceSkinny> prices;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<ItemPriceSkinny> getPrices() {
        return prices;
    }

    public void setPrices(List<ItemPriceSkinny> prices) {
        this.prices = prices;
    }
}
