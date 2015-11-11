package com.walkingdevs.immigrate;

import android.app.Application;
import android.content.Context;

/**
 * Created by dan on 10/26/15.
 */
public class MyApp extends Application {

    private static Context context;

    public void onCreate(){
        super.onCreate();
        MyApp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApp.context;
    }
}