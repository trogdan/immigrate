package com.walkingdevs.immigrate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class CompareActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        Intent intent = getIntent();

        //TODO: get extras from intent
    }

}
