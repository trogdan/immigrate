package com.walkingdevs.immigrate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.thewalkingdevs.api.myApi.MyApi;
import com.thewalkingdevs.api.myApi.model.CityBag;
import com.thewalkingdevs.api.myApi.model.ItemPriceSkinny;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class CityViewFragment extends Fragment {

    private ListView mListView;
    private ItemPriceAdapter mPricesAdapter;

    public CityViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_city_view, container, false);

        mPricesAdapter = new ItemPriceAdapter(getActivity(), new ArrayList<ItemPriceSkinny>());
        mListView = (ListView) rootView.findViewById(R.id.listview);
        mListView.setAdapter(mPricesAdapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(MyApp.mCurrentLocation != null && MyApp.mCurrentLocation.getCity() != null) {
            new Endpoints2AsyncTask().execute(MyApp.mCurrentLocation.getCity());
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

            List<ItemPriceSkinny> cleanedPriceList = new ArrayList<ItemPriceSkinny>();
            for (ItemPriceSkinny price : priceList){
                // in an ideal world this would be written in a more obvious way
                if (price.getHighestPrice() == 0 && price.getLowestPrice() == 0){
                    //nothing
                } else {
                    cleanedPriceList.add(price);
                }
            }

            mPricesAdapter.clear();
            for (int i = 0; i < cleanedPriceList.size(); i++) {
                mPricesAdapter.add(cleanedPriceList.get(i));
            }
        }
    }
}