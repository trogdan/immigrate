package com.walkingdevs.immigrate;

import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.thewalkingdevs.api.myApi.MyApi;
import com.thewalkingdevs.api.myApi.model.CityPrices;
import com.thewalkingdevs.api.myApi.model.Places;

import java.util.List;


public class NeighborhoodSliderFragment extends Fragment {

    private static MyApi myApiService = null;
    private static final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();

    private Places mLatestPlaces;

    public NeighborhoodSliderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_neighborhood_slider, container, false);
    }

    public Places getPlaces()
    {
        return mLatestPlaces;
    }

    /**
     * Async Task for Endpoints.
     */
    public class PlacesAsyncTask extends AsyncTask<String, Void, Places> {

        @Override
        protected Places doInBackground(String... params) {

            // core doInBackground code from https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
            if(myApiService == null) {
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://brilliant-brand-112216.appspot.com/_ah/api/");
                myApiService = builder.build();
            }

            String location = params[0];
            try {
                return myApiService.getPlaces(location).execute();
            } catch (Exception e) {
                Log.e(LOG_TAG, "Error getting api service places");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Places places) {
            super.onPostExecute(places);
            mLatestPlaces = places;
        }
    }
}
