package com.thewalkingdevs.api.model;

import com.google.appengine.repackaged.org.codehaus.jackson.annotate.JsonProperty;

/**
 * Data model for Item Price.
 */
public class ItemPrice {
    @JsonProperty("item_id")
    int itemId;
    @JsonProperty("lowest_price")
    int lowestPrice;
    @JsonProperty("average_price")
    int averagePrice;
    @JsonProperty("highest_price")
    int highestPrice;
    @JsonProperty("item_name")
    String itemName;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(int lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public int getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(int averagePrice) {
        this.averagePrice = averagePrice;
    }

    public int getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(int highestPrice) {
        this.highestPrice = highestPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
