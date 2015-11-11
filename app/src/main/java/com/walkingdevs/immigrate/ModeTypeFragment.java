package com.walkingdevs.immigrate;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ModeTypeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;


    public ModeTypeFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_mode_type, container, false);

        // TODO populate cards and pull from numbeo
        LocationObj currentLocation = MyApp.getLocation();

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

    /**
     * The method that is called when you want to set up the mode type fragment
     */
    public void loadModeTypeSelection(LocationObj location){
        Log.d("loadModeType", "You made it!!");
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

}
