package com.walkingdevs.immigrate;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.thewalkingdevs.api.myApi.MyApi;
import com.thewalkingdevs.api.myApi.model.CityBag;
import com.thewalkingdevs.api.myApi.model.ItemPriceSkinny;
import com.thewalkingdevs.api.myApi.model.Place;
import com.thewalkingdevs.api.myApi.model.Places;

import java.text.DecimalFormat;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ModeTypeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ModeTypeFragment extends Fragment {

    //TextView variables
    private TextView neighbourhoodPreviewText;
    private TextView cityPreviewText;
    private Boolean cityPreviewUpdated = false;
    private Boolean neighbourhoodPreviewUpdated = false;

    private LocationObj currentLocation;

    private OnFragmentInteractionListener mListener;

    public ModeTypeFragment() {
        // Required empty public constructor
    }

    public void updateNeighbourhoodPreview(Place place){

        if (place != null){

            float distFrom = place.getDistFrom();
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            String formattedDistance = df.format(distFrom);

            String previewText = "Nearest " + place.getName() + " is "
                    + formattedDistance + " miles away.";

            if (neighbourhoodPreviewText != null){
                neighbourhoodPreviewUpdated = true;
                neighbourhoodPreviewText.setText(previewText);
            }

        }
    }

    public void updateCityPreview(ItemPriceSkinny itemPriceSkinny){
        if (itemPriceSkinny != null){
            Integer highPrice = itemPriceSkinny.getHighestPrice();
            Integer lowPrice = itemPriceSkinny.getLowestPrice();
            String previewText = "One bedroom apartment downtown costs between $" + lowPrice
                    + " and $" + highPrice + ".";

            if (cityPreviewText != null){

                cityPreviewUpdated = true;
                cityPreviewText.setText(previewText);
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mode_type, container, false);
        neighbourhoodPreviewText = (TextView) rootView.findViewById(R.id.neighbourhood_preview);
        cityPreviewText = (TextView) rootView.findViewById(R.id.city_preview);

        currentLocation = MyApp.getInstance().getLocation();

        if(currentLocation != null && (!neighbourhoodPreviewUpdated && !cityPreviewUpdated)) {
            String location = currentLocation.getLatitudes() + "," + currentLocation.getLongitude();
            new PlacesEndpointAsyncTask().execute(location);
            new Endpoints2AsyncTask().execute(location);
        }

        // TODO: populate cards and pull from numbeo
        LocationObj currentLocation = MyApp.getInstance().getLocation();

        CardView cityCard = (CardView) rootView.findViewById(R.id.mode_card_city);
        cityCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Launch new activity if you got a location
                Intent intent = new Intent(getActivity(), CityView.class);
                startActivity(intent);
            }
        });
        CardView neighborhoodCard = (CardView) rootView.findViewById(R.id.mode_card_neighborhood);
        neighborhoodCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Launch new activity if you got a location
                Intent intent = new Intent(getActivity(), NeighborhoodActivity.class);
                startActivity(intent);
            }
        });
        return rootView;

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    /**
     * Async Task for Endpoints.
     */
    public class PlacesEndpointAsyncTask extends AsyncTask<String, Void, Places> {

        private MyApi myApiService = null;

        @Override
        protected Places doInBackground(String... params) {

            // core doInBackground code from https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
            if(myApiService == null) {
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://brilliant-brand-112216.appspot.com/_ah/api/");
                myApiService = builder.build();
            }

            // Get the city info
            String city = params[0];

            try {
                return myApiService.getPlaces(city).execute();
            } catch (Exception e) {
                Log.d("test", e.getMessage());
            }//IOException e) {
            // return e.getMessage();*/
            return null;

        }

        @Override
        public void onPostExecute(Places p){
            List<Place> placesResults = p.getResults();
            if (placesResults != null && !placesResults.isEmpty()) {
                Place firstPlace = placesResults.get(0);
                ModeTypeFragment.this.updateNeighbourhoodPreview(firstPlace);
            }
        }

    }

    public class Endpoints2AsyncTask extends AsyncTask<String, Void, CityBag> {

        private MyApi myApiService = null;
        private final String LOG_TAG = Endpoints2AsyncTask.class.getSimpleName();

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

        @Override
        protected void onPostExecute(CityBag cityBag) {
            super.onPostExecute(cityBag);

            List<ItemPriceSkinny> priceList = cityBag.getCityPrices().getPrices();
            if (!priceList.isEmpty() && priceList.size() > 4)
             ModeTypeFragment.this.updateCityPreview(priceList.get(5));

        }
    }

}
