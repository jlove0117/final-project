package com.cst2335.finalproject;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OCTrip extends Activity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_octrip);
//    }
    public class BusRoute {
    private String routeNum;
    private String directionID;
    private String direction;
    private String routeHeading;

    public BusRoute() {
        this("", "", "", "");
    }

    public BusRoute(String routeNum, String directionID, String direction, String routeHeading) {
        this.routeNum = routeNum;
        this.directionID = directionID;
        this.direction = direction;
        this.routeHeading = routeHeading;
    }

    public String getRouteNum() {
        return routeNum;
    }

    public void setRouteNum(String routeNum) {
        this.routeNum = routeNum;
    }

    public String getDirectionID() {
        return directionID;
    }

    public void setDirectionID(String directionID) {
        this.directionID = directionID;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getRouteHeading() {
        return routeHeading;
    }

    public void setRouteHeading(String routeHeading) {
        this.routeHeading = routeHeading;
    }
//
//        @NonNull
//        @Override
//        public String toString() {
//            return "Route: " +
//                    routeNum +
//                    System.lineSeparator() +
//                    "Direction: " +
//                    direction +
//                    System.lineSeparator() +
//                    "Route Heading: " +
//                    routeHeading;
//        }
    }
}
