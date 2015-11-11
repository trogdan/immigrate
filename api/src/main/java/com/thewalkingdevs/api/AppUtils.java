package com.thewalkingdevs.api;

import com.thewalkingdevs.api.model.Location;
import com.thewalkingdevs.api.model.Place;

/**
 * Created by levani on 11/10/15.
 */
public class AppUtils {

    private static float distFrom(float lat1, float lng1, float lat2, float lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);

        return dist;
    }

    public static float getPlaceDistance(Place destination, String origin) {

        Location destinationLocation = destination.getGeometry().getLocation();
        String[] originalLocation = origin.split(",");
        float lat1 = Float.parseFloat(originalLocation[0]);
        float lng1 = Float.parseFloat(originalLocation[1]);
        float lat2 = destinationLocation.getLat();
        float lng2 = destinationLocation.getLng();

        float dist = distFrom(lat1, lng1, lat2, lng2);

        return convertMetersToMiles(dist);
    }

    private static float convertMetersToMiles(float meters) {
        return (meters / 1609.344f);
    }
}
