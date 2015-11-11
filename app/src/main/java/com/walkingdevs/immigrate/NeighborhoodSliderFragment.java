package com.walkingdevs.immigrate;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.thewalkingdevs.api.myApi.model.Places;
import com.thewalkingdevs.api.myApi.model.Place;

import java.util.ArrayList;
import java.util.List;


public class NeighborhoodSliderFragment extends Fragment {

    private Places mLatestPlaces;

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

        return rootView;
    }

    public void setLatestPlaces(Places places)
    {
        mLatestPlaces = places;
        mPlacesAdapter.clear();
        for(int i = 0; i < mLatestPlaces.getResults().size(); i++)
        {
            Place place = mLatestPlaces.getResults().get(i);
            mPlacesAdapter.add(place);
        }
    }

    public class ViewHolder {
        public TextView placeNameText;
    }
    public class PlaceAdapter extends ArrayAdapter<Place> {

        private ViewHolder views;
        public PlaceAdapter(Context context, List<Place> places) {
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
            if(place != null) {
                String name = place.getName();
                views.placeNameText.setText(name);
            }
            // Return the completed view to render on screen
            return convertView;
        }
    }


}
