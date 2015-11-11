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
import com.thewalkingdevs.api.myApi.model.Place;
import com.thewalkingdevs.api.myApi.model.Places;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ModeTypeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ModeTypeFragment extends Fragment {

    //TextView variables
    private TextView t1;
    private TextView d1;
    private TextView t2;
    private TextView d2;

    private LocationObj currentLocation;
    private PlacesEndpointAsyncTask placesEndpointAsyncTask;

    private OnFragmentInteractionListener mListener;


    public ModeTypeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Define text view
//        t1 = (TextView) getActivity().findViewById(R.id.neighbourhoodTitle);
//        d1 = (TextView) getActivity().findViewById(R.id.neighbourhoodDesc);
//        t2 = (TextView) getActivity().findViewById(R.id.neighbourhoodTitle2);
//        d2 = (TextView) getActivity().findViewById(R.id.neighbourhoodDesc2);


        String coordinates = "42.4183333,-71.1066667";


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_mode_type, container, false);

        currentLocation = MyApp.getInstance().getLocation();

        placesEndpointAsyncTask = new PlacesEndpointAsyncTask();

        if(currentLocation != null){
            //placesEndpointAsyncTask.execute(currentLocation.getLatitudes() + "," + currentLocation.getLongitude());
        }

        //Define text view
//        t1 = (TextView) rootView.findViewById(R.id.neighbourhoodTitle);
//        d1 = (TextView) rootView.findViewById(R.id.neighbourhoodDesc);
//        t2 = (TextView) rootView.findViewById(R.id.neighbourhoodTitle2);
//        d2 = (TextView) rootView.findViewById(R.id.neighbourhoodDesc2);

        // TODO populate cards and pull from numbeo
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

            List<Place> place = p.getResults();
            Place p1 = place.get(0);
            Place p2 = place.get(1);

            //Populate the UI
            t1.setText(p1.getDescription());
            d1.setText("hi");
            t2.setText(p2.getDescription());
            d2.setText("hi");
        }

    }

}
