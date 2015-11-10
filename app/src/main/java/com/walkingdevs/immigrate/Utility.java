package com.walkingdevs.immigrate;

import java.util.ArrayList;

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
}
