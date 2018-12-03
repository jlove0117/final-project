package com.cst2335.finalproject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static org.xmlpull.v1.XmlPullParser.START_TAG;

public class OCTranspoBusRouteDetails extends Activity {

    protected final static String ACTIVITY_NAME = "OCTranspoRouteDetails";
    private TextView routeHeading, routeNumber, routeDirection, routeDirectionID;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_octranspo_bus_route_details);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        routeHeading = findViewById(R.id.routeHeading);
        routeNumber = findViewById(R.id.routeNumber);
        routeDirection = findViewById(R.id.routeDirection);
        routeDirectionID = findViewById(R.id.routeDirectionID);

        OCTranpoQuery runQuery = new OCTranpoQuery();
        runQuery.execute(URL);
    }

    public class OCTranpoQuery extends AsyncTask<String, Integer, String> {

        private String route_heading;
        private String route_number;
        private String route_direction;
        private String route_direction_id;

        @Override
        protected String doInBackground(String... strings) {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            try {
                /* The URL for the web browser */
                URL url = new URL
                        ("view-source:https://api.octranspo1.com/v1.2/GetRouteSummaryForStop?appID=223eb5c3&&apiKey=ab27db5b435b8c8819ffb8095328e775&stopNo=3050");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                // Given a string representation of a URL, sets up a connection and gets
                // an input stream.
                urlConnection.setReadTimeout(10000); // milliseconds
                urlConnection.setConnectTimeout(15000); // milliseconds
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
                /* The Query is starting */
                urlConnection.connect();

                /* Creating a Pull parser uses the Factory pattern */
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser parser = factory.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(urlConnection.getInputStream(), "UTF-8");

                while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT){
                    if(xmlPullParser.getEventType()== xmlPullParser.START_TAG) {
                        Log.i(ACTIVITY_NAME, "Iterating the XML tags");
                        System.out.println(xmlPullParser.getName());
                        if (xmlPullParser.getName().equals("RouteNo")) {
                            route_number = xmlPullParser.getAttributeValue(null, "value");
                            publishProgress(25);
                            Log.i(ACTIVITY_NAME, "Route number is working");
                        }
                        if (xmlPullParser.getName().equals("DirectionID")) {
                            route_direction_id = xmlPullParser.getAttributeValue(null, "value");
                            publishProgress(50);
                            Log.i(ACTIVITY_NAME, "Route direction ID is working");
                        }
                        if (xmlPullParser.getName().equals("Direction")) {
                            route_direction = xmlPullParser.getAttributeValue(null, "value");
                            publishProgress(75);
                            Log.i(ACTIVITY_NAME, "Route direction is working");
                        }
                        if (xmlPullParser.getName().equals("RouteHeading")) {
                            route_heading = xmlPullParser.getAttributeValue(null, "value");
                            publishProgress(100);
                            Log.i(ACTIVITY_NAME, "Current temperature is working");
                        }

                    }
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i(ACTIVITY_NAME, "The background has been finished");
            return "Done";
        }

        public boolean fileExistence(String fname){
            File file = getBaseContext().getFileStreamPath(fname);
            return file.exists();
        }

        @Override
        protected void onProgressUpdate(Integer... value) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(value[0]);
            Log.i(ACTIVITY_NAME, "Progress Update");
        }

        protected void onPostExecute(String[] result) {
            routeHeading.setText("Route Heading: " + route_heading);
            routeNumber.setText("Route Number: " + route_number);
            routeDirection.setText("Route Direction: " + route_direction);
            routeDirectionID.setText("Route Direction ID: " + route_direction_id);
            Log.i(ACTIVITY_NAME, "Post Execution");
        }
    }
}
