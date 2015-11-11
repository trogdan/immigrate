package com.thewalkingdevs.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data model for Item Price.
 */
public class ItemPriceSkinny {
    @JsonProperty("lowest_price")
    private int lowestPrice;
    @JsonProperty("highest_price")
    private int highestPrice;
    @JsonProperty("item_name")
    String itemName;
    private int itemId;

    public int getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(int lowestPrice) {
        this.lowestPrice = lowestPrice;
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

    public int getItemId() { return itemId; }

    public void setItemId(int itemId) { this.itemId = itemId; }
}
