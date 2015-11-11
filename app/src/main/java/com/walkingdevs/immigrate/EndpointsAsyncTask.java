package com.walkingdevs.immigrate;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.thewalkingdevs.api.myApi.MyApi;
import com.thewalkingdevs.api.myApi.model.CityBag;
import com.thewalkingdevs.api.myApi.model.CityPrices;

/**
 * Async Task for Endpoints.
 */
public class EndpointsAsyncTask extends AsyncTask<String, Void, CityBag> {

    private static MyApi myApiService = null;
    private static final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();

    @Override
    protected CityBag doInBackground(String... params) {

        // core doInBackground code from https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                   .setRootUrl("https://brilliant-brand-112216.appspot.com/_ah/api/");
            myApiService = builder.build();
        }

        // Get the city info
        String city = params[0];

        try {
           return myApiService.getCityBag(city).execute();
        } catch (Exception e) {}//IOException e) {
           // return e.getMessage();*/
        return null;
    }

}
