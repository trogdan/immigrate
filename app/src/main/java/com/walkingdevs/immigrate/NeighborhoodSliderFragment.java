package com.walkingdevs.immigrate;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.thewalkingdevs.api.myApi.MyApi;
import com.thewalkingdevs.api.myApi.model.Places;
import com.thewalkingdevs.api.myApi.model.Place;

import java.util.ArrayList;
import java.util.List;


public class NeighborhoodSliderFragment extends Fragment {

    private static MyApi myApiService = null;

    private Places mLatestPlaces;
    private LocationObj mCurrentLocation;
    private PlacesAsyncTask mPlacesTask;
    private ListView mListView;
    private PlaceAdapter mPlacesAdapter;

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
        View rootView = inflater.inflate(R.layout.fragment_neighborhood_slider, container, false);

        mPlacesAdapter = new PlaceAdapter(getActivity(), new ArrayList<Place>());
        mListView = (ListView) rootView.findViewById(R.id.sliderListView);
        mListView.setAdapter(mPlacesAdapter);

        mCurrentLocation = MyApp.getInstance().getLocation();
        mPlacesTask = new PlacesAsyncTask();
        mPlacesTask.execute(mCurrentLocation.getLatitudes() + "," + mCurrentLocation.getLongitude());


        return rootView;
    }

    public Places getPlaces()
    {
        return mLatestPlaces;
    }

    /**
     * Async Task for Endpoints.
     */
    public class PlacesAsyncTask extends AsyncTask<String, Void, Places> {
        private final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();

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
                Log.e(LOG_TAG, e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Places places) {
            super.onPostExecute(places);

            List<Place> placeList = places.getResults();

            mPlacesAdapter.clear();
            for (int i = 0; i < placeList.size(); i++) {
                mPlacesAdapter.add(placeList.get(i));
            }

            mLatestPlaces = places;
        }
    }

    public class ViewHolder {
        public TextView placeNameText;
    }
    public class PlaceAdapter extends ArrayAdapter<Place> {

        private ViewHolder views;
        public PlaceAdapter(Context context, ArrayList<Place> places) {
            super(context, 0, places);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Place place = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_neighborhood, parent, false);
                views = new ViewHolder();
                views.placeNameText = (TextView)convertView.findViewById(R.id.item_place_name_text);
            }
            views.placeNameText.setText(place.getName());

            // Return the completed view to render on screen
            return convertView;
        }
    }


}
