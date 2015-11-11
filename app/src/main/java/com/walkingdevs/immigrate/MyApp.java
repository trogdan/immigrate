package com.walkingdevs.immigrate;

import android.app.Application;
import android.content.Context;

/**
 * Created by dan on 10/26/15.
 */
public class MyApp extends Application {

    private static MyApp mInstance;
    private static Context context;
    public static LocationObj mCurrentLocation;

    public static MyApp getInstance() {
        return mInstance;
    }

    public void onCreate(){
        super.onCreate();
        MyApp.context = getApplicationContext();
        mInstance = this;
    }

    public static Context getAppContext() {
        return MyApp.context;
    }

    public static void setLocation(LocationObj currentLocation) {
        mCurrentLocation = currentLocation;
    }

    public static LocationObj getLocation() { return mCurrentLocation; }
}