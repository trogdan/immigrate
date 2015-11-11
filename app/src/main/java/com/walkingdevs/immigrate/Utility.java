package com.walkingdevs.immigrate;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

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

        return locObj;
    }
}
