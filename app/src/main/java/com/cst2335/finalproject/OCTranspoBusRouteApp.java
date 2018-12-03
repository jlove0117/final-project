package com.cst2335.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class OCTranspoBusRouteApp extends Activity {

    protected static final String ACTIVITY_NAME = "OCTranspoBusRouteApp";
    private String destination;
    private double latitude;
    private double longitude;
    private String startTime;
    private String gpsSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_octranspo_bus_route_app);
        Log.i(ACTIVITY_NAME, "In onCreate");

    }

    Button busButton;


}
