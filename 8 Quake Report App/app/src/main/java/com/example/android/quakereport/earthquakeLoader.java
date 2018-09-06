package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sidak Pasricha on 06-Jul-18.
 */

public class earthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private final String LOG_TAG = getClass().getName();

    String mUrl = "";

    public earthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        if (mUrl == "") {
            return null;
        }

        String response = "";
        List<Earthquake> earthquakes = new ArrayList<>();
        // Fetch Earthquake Data
        try {
            response = QueryUtils.makeHttpRequest(mUrl);
            Log.d(LOG_TAG, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Convert JSON to List of Earthquakes
        if(!response.equals("")) {
            earthquakes = QueryUtils.extractEarthquakes(response);
        }
        return earthquakes;
    }
}
