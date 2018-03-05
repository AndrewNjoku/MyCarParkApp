package com.example.andriatae.mymapproject.mymapproject2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.andriatae.mymapproject.Network.AnalyticsApplication;
import com.example.andriatae.mymapproject.R;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class testactivity2 extends AppCompatActivity {

    private String TAG;
    private Tracker mTracker;
    AnalyticsApplication application= (AnalyticsApplication) getApplication();
    private String name = "testactivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testactivity2);


        mTracker=application.getDefaultTracker();

        Log.i(TAG, "Started activity: " + name);
        mTracker.setScreenName("Image~" + name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }


    @Override
    protected void onResume() {
        super.onResume();

        Log.i(TAG, "back to screen: " + name);
        mTracker.setScreenName("Image~" + name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());



    }
}
