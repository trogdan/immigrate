package com.walkingdevs.immigrate;

import java.util.ArrayList;

/**
 * Created by aevangelista on 15-11-09.
 */
public class LocationObj {

    String placeId;
    String description;
    ArrayList<String> locationTerms = new ArrayList<String>();

    public void setPlaceId(String p){
        placeId = p;
    }

    public void setDescription(String d){
        description = d;
    }

    public void setLocationTerms(ArrayList<String> l){
        locationTerms = l;
    }

    public String getPlaceId(){
        return placeId;
    }

    public String getDescription(){
        return description;
    }

    public ArrayList<String> getLocationTerms(){
        return locationTerms;
    }

    public String toString(){

        StringBuilder terms = new StringBuilder();

        for(int i = 0; i < locationTerms.size();i++){
            terms.append(" " + locationTerms.get(i).toString());
        }

        //return terms.toString();

        String returnString = "Place ID: " + placeId + "\n" +
                "Description: " + description + "\n" +
                "Terms: " + terms.toString() + "\n";

        return returnString;
    }

}
