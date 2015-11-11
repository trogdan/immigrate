package com.walkingdevs.immigrate;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.api.client.util.Lists;
import com.thewalkingdevs.api.myApi.model.CityCrime;
import com.thewalkingdevs.api.myApi.model.CityCrimeSkinny;
import com.thewalkingdevs.api.myApi.model.CityHealthCareInfo;
import com.thewalkingdevs.api.myApi.model.CityHealthCareInfoSkinny;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by aevangelista on 15-11-09.
 */
public class Utility {

    /**
     * Helper method to test if we got a full address or only a city
     * @param locationTerms the ArrayList of terms pulled from Autocomplete
     * @return 0 = address, 1 = city, 2 = error (possibly country)
     */
    public static int locationType(ArrayList<String> locationTerms) {

        int locationType;
        if(locationTerms.size() == 3) {
            locationType = 1;
        } else if(locationTerms.size() > 3) {
            locationType = 0;
        } else {
            locationType = 2;
        }

        return locationType;
    }

    public static LocationObj convertLatLongToAddress(Context context, double lat, double lon) {

        Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
        String myAddress = null;
        LocationObj locObj = new LocationObj();
        ArrayList<String> locationTerms = new ArrayList<String>();

        locObj.setLatitudes(lat);
        locObj.setLongitude(lon);

        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);

            if(addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder();
                locObj.setCity(returnedAddress.getLocality());

                for(int i=0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    String[] commaSplit = null;
                    if(returnedAddress.getAddressLine(i).contains(",")) {
                        commaSplit = returnedAddress.getAddressLine(i).split(",");
                        for(int x = 0; x < commaSplit.length; x++) {
                            strReturnedAddress.append(commaSplit[x]).append(", ");
                            locationTerms.add(commaSplit[x]);
                        }
                    }
                    if(i == returnedAddress.getMaxAddressLineIndex()) {
                        strReturnedAddress.append(returnedAddress.getAddressLine(i));
                        myAddress = strReturnedAddress.toString();
                    } else if (commaSplit == null) {
                        strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(", ");
                        myAddress = strReturnedAddress.toString();
                    }
                    locationTerms.add(returnedAddress.getAddressLine(i));
                }
            }
            else{
                myAddress = ("No Address returned!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            myAddress = "Canont get Address!";
        }

        locObj.setDescription(myAddress);
        locObj.setLocationTerms(locationTerms);
        MyApp myApp = MyApp.getInstance();
        myApp.mCurrentLocation = locObj;

        return locObj;
    }

    /**
     * Returns appropriate strings for healthcare info in the order:
     *      1. accuracy
     *      2. responsiveness
     *      3. friendliness
     *      4. healthcare cost
     *      5. competency
     *      6. speed
     *      7. mordern equipment
     *
     * @param context the context to use
     * @param info the info to use
     * @return a list of representative strings
     */
    public static List<String> normalizeCityHealthCareInfo(Activity context, CityHealthCareInfoSkinny info) {
        // First get the leading index
        List<Double> values = new ArrayList<Double>();

        values.add(info.getAccuracyIndex());
        values.add(info.getResponsivenessIndex());
        values.add(info.getFriendlinessIndex());
        values.add(info.getHealthCareCost());
        values.add(info.getHealthCareStaffCompetency());
        values.add(info.getSpeedIndex());
        values.add(Double.valueOf(String.valueOf(info.getModernEquipmentIndex())));

        Double leadingIndex = findLeadingIndex(values);

        return normalizeValues(context, values, leadingIndex);
    }

    /**
     * Returns appropriate strings for healthcare info in the order:
     *      1. corruption index
     *      2. crime level
     *      3. daylight safety index
     *      4. night safety index
     *      5. drug crime index
     *      6. diversity threat index
     *      7. violent crime index
     *
     * @param context the context to use
     * @param cityCrimeInfo the info to use
     * @return a list of representative strings
     */
    public static List<String> normalizeCityCrime(Activity context, CityCrimeSkinny cityCrimeInfo) {
        List<Double> values = new ArrayList<Double>();

        values.add(cityCrimeInfo.getCorruptionIndex());
        values.add(cityCrimeInfo.getCrimeLevel());
        values.add(cityCrimeInfo.getDaylightSafetyIndex());
        values.add(cityCrimeInfo.getNightSafetyIndex());
        values.add(cityCrimeInfo.getDrugCrimeIndex());
        values.add(cityCrimeInfo.getDiversityThreatIndex());
        values.add(cityCrimeInfo.getViolentCrimeIndex());

        Double leadingIndex = findLeadingIndex(values);

        return normalizeValues(context, values, leadingIndex);
    }

    private static List<String> normalizeValues(Activity context, List<Double> values, Double leadingIndex) {
        List<String> strs = new ArrayList<String>();

        for (Double value : values) {
            double index = value / leadingIndex * 100.00;
            strs.add(normalize(context, index));
        }

        return strs;
    }

    private static double findLeadingIndex(List<Double> values) {
        Double max = null;

        for(Double d : values) {
            if (d.doubleValue() > max) {
                max = d.doubleValue();
            }
        }

        return max;
    }

    private static String normalize(Activity context, double value) {
        if (value <= 0.0) {
            return context.getString(R.string.normalize_value_poor);
        } else if (value > 25.00 && value <= 50.00) {
            return context.getString(R.string.normalize_value_good);
        } else if (value > 50.00 && value <= 75.00) {
            return context.getString(R.string.normalize_value_very_good);
        }

        return context.getString(R.string.normalize_value_excellent);
    }

}
