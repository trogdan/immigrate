package com.walkingdevs.immigrate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.thewalkingdevs.api.myApi.MyApi;
import com.thewalkingdevs.api.myApi.model.Place;
import com.thewalkingdevs.api.myApi.model.Places;
import com.thewalkingdevs.api.myApi.model.PlacesBag;

import java.util.ArrayList;
import java.util.List;

public class NeighborhoodActivity extends AppCompatActivity implements OnMapReadyCallback  {

    private static final int NUM_PAGES = 3;
    private static MyApi myApiService;
    private PlacesAsyncTask mPlacesTask;
    private LocationObj mCurrentLocation;
    private PlacesBag mLatestPlaces;
    private ArrayList<NeighborhoodSliderFragment> mCurrentFragments;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    private static String[] mPageTitles;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighborhood);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPageTitles = new String[] {
                getResources().getString(R.string.tab_title_services),
                getResources().getString(R.string.tab_title_essentials),
                getResources().getString(R.string.tab_title_transportation)
        };

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mCurrentLocation = MyApp.getInstance().getLocation();


        mCurrentFragments = new ArrayList<>();
        mCurrentFragments.add(new NeighborhoodSliderFragment());
        mCurrentFragments.add(new NeighborhoodSliderFragment());
        mCurrentFragments.add(new NeighborhoodSliderFragment());

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.neighborhood_pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOffscreenPageLimit(NUM_PAGES);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateMap();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mPlacesTask = new PlacesAsyncTask();
        mPlacesTask.execute(mCurrentLocation.getLatitudes() + "," + mCurrentLocation.getLongitude());

    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we
     * just add a marker near Africa.
     */
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(mCurrentLocation.getLatitudes(), mCurrentLocation.getLongitude()),
                10));
        map.setMyLocationEnabled(true);
        updateMap();
    }

    public void updateMap()
    {
        if (mLatestPlaces != null)
        {
            int item = mPager.getCurrentItem();
            Places places = null;
            if (item == 0) {
                places = mLatestPlaces.getServices();
            } else if (item == 1) {
                places = mLatestPlaces.getEssentials();
            } else if (item == 2) {
                places = mLatestPlaces.getTransportation();
            }

            if (places != null)
            {
                mMap.clear();
                for(int i = 0; i < places.getResults().size(); i++)
                {
                    Place place = places.getResults().get(i);

                    mMap.addMarker(new MarkerOptions().position(new LatLng(
                            place.getGeometry().getLocation().getLat(),
                            place.getGeometry().getLocation().getLng())).title(place.getName()));
                }
            }
        }
    }
    /**
     * A simple pager adapter that represents 3 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if(mLatestPlaces != null) {
                if (position == 0) {
                    mCurrentFragments.get(position).setLatestPlaces(mLatestPlaces.getServices());
                } else if (position == 1) {
                    mCurrentFragments.get(position).setLatestPlaces(mLatestPlaces.getEssentials());
                } else if (position == 2) {
                    mCurrentFragments.get(position).setLatestPlaces(mLatestPlaces.getTransportation());
                }
            }

            //Draw here?
            return mCurrentFragments.get(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position > mPageTitles.length)
                return "";
            else
                return mPageTitles[position];
        }
    }

    /**
     * Async Task for Endpoints.
     */
    public class PlacesAsyncTask extends AsyncTask<String, Void, PlacesBag> {
        private final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();

        @Override
        protected PlacesBag doInBackground(String... params) {

            // core doInBackground code from https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
            if(myApiService == null) {
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://brilliant-brand-112216.appspot.com/_ah/api/");
                myApiService = builder.build();
            }

            String location = params[0];
            try {
                return myApiService.getPlacesBag(location).execute();

            } catch (Exception e) {

                Log.e(LOG_TAG, "Error getting api service places");
                Log.e(LOG_TAG, e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(PlacesBag placeBag) {
            super.onPostExecute(placeBag);

            if(placeBag != null) {
                mLatestPlaces = placeBag;
                for(int i = 0; i < NUM_PAGES; i++)
                {
                    if (i == 0) {
                        mCurrentFragments.get(i).setLatestPlaces(mLatestPlaces.getServices());
                    } else if (i == 1) {
                        mCurrentFragments.get(i).setLatestPlaces(mLatestPlaces.getEssentials());
                    } else if (i == 2) {
                        mCurrentFragments.get(i).setLatestPlaces(mLatestPlaces.getTransportation());
                    }
                }

                updateMap();
            }
        }
    }
}
