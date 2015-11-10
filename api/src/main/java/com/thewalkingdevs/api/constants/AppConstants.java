package com.thewalkingdevs.api.constants;

/**
 * Created by levani on 11/10/15.
 */
public class AppConstants {

    // Places API
    public static final String MESSAGE_CONNECTION_ERROR = "Connection error";
    public static final String PLACES_QUERY_BASE = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
    public static final String PLACES_QUERY_LOCATION = "location";
    public static final String PLACES_QUERY_RADIUS = "radius";
    public static final String PLACES_QUERY_NAME = "name";
    public static final String PLACES_QUERY_API_KEY = "key";

    // Numbeo API
    public static final String NUMBEO_QUERY_BASE = "http://www.numbeo.com/api";
    public static final String NUMBEO_CITY_PRICES_ENDPOINT = "city_prices?";
    public static final String NUMBEO_INDICES_ENDPOINT = "indices?";
    public static final String NUMBEO_API_KEY_PREFIX = "api_key=";
    public static final String NUMBEO_API_KEY_VALUE = "***REMOVED***";

}
