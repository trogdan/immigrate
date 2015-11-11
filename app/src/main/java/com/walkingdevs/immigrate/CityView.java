package com.walkingdevs.immigrate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;

public class CityView extends AppCompatActivity {

    private String mCompareInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CityViewFragment cvf = new CityViewFragment();
        //cvf.setArguments(data);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, cvf, "CITY_TAG")
                    .commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_compare);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onCompareClick(View view){

        if(MyApp.mCurrentLocation != null && MyApp.mCurrentLocation.getCity() != null) {

            String currentCity = MyApp.mCurrentLocation.getCity();
            String compareToCity = mCompareInput;

            Intent intent = new Intent(this, CityViewFragment.class);
            intent.putExtra("currentCity", currentCity);
            intent.putExtra("compareToCity", compareToCity);
            startActivity(intent);
        }

        Log.d("LOG_TAG", mCompareInput);
    }

    public void updateInput(String compareInput){
        mCompareInput = compareInput;
    }

}
