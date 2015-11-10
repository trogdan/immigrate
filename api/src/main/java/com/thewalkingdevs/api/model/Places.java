package com.thewalkingdevs.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Places {

    @JsonProperty("html_attributions")
    private List<String> htmlAttributions = null;
    @JsonProperty("next_page_token")
    private String nextPageToken = null;
    @JsonProperty("results")
    private List<Place> results = null;
    @JsonProperty("status")
    private String status = null;

    private String myData;

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }

    public List<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<String> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<Place> getResults() {
        return results;
    }

    public void setResults(List<Place> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
