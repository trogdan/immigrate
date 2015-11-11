package com.walkingdevs.immigrate;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.thewalkingdevs.api.myApi.model.Geometry;
import com.thewalkingdevs.api.myApi.model.Location;
import com.thewalkingdevs.api.myApi.model.Place;
import com.thewalkingdevs.api.myApi.model.Places;

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
        public TextView placeDescriptionText;
        public TextView placeDistanceFromText;
    }
    public class PlaceAdapter extends ArrayAdapter<Place> {

        private ViewHolder views;
        public PlaceAdapter(Context context, List<Place> places) {
            super(context, 0, places);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Place place = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_neighborhood, parent, false);
                views = new ViewHolder();
                views.placeNameText = (TextView)convertView.findViewById(R.id.item_place_name_text);
                views.placeDescriptionText = (TextView) convertView.findViewById(R.id.item_place_description_text);
                views.placeDistanceFromText = (TextView) convertView.findViewById(R.id.item_place_distance_text);
            }
            if(place != null) {
                String name = place.getName();
                views.placeNameText.setText(name);
                String description = place.getDescription();
                views.placeDescriptionText.setText(description);
                String distance = String.format("%.2f", place.getDistFrom()) + " mi";
                views.placeDistanceFromText.setText(distance);

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Geometry geo = place.getGeometry();
                        Location loc = geo.getLocation();

                        Uri uri = Uri.parse("geo:0,0?q=" + loc.getLat()+"," + loc.getLng() + "(" + place.getName() + ")");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
            }
            // Return the completed view to render on screen
            return convertView;
        }
    }


}
