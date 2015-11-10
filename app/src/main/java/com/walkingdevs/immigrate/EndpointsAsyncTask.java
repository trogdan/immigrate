package com.walkingdevs.immigrate;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.thewalkingdevs.api.myApi.MyApi;
import com.thewalkingdevs.api.myApi.model.CityPrices;



/**
 * Async Task for Endpoints.
 */
public class EndpointsAsyncTask extends AsyncTask<String, Void, CityPrices> {

    private static MyApi myApiService = null;
    private static final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();

    @Override
    protected CityPrices doInBackground(String... params) {

        // core doInBackground code from https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                   .setRootUrl("https://brilliant-brand-112216.appspot.com/_ah/api/");
            myApiService = builder.build();
        }

        try {
           return myApiService.getCityPrices().execute();
        } catch (Exception e) {}//IOException e) {
           // return e.getMessage();*/
        return null;
    }

}
