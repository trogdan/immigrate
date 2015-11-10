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

    public static String convertLatLongToAddress(Context context, double lat, double lon) {

        Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
        String myAddress;

        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);

            if(addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder();
                for(int i=0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    if(i == returnedAddress.getMaxAddressLineIndex()) {
                        strReturnedAddress.append(returnedAddress.getAddressLine(i));
                    } else {
                        strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(", ");
                    }

                }
                myAddress = strReturnedAddress.toString();
            }
            else{
                myAddress = ("No Address returned!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            myAddress = "Canont get Address!";
        }

        return myAddress;
    }
}
