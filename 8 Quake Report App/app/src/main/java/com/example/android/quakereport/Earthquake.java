package com.example.android.quakereport;

/**
 * Created by Sidak Pasricha on 23/01/2018.
 */

public class Earthquake {
    double mMagnitude;
    String mLocation;
    long mtimeInMilliseconds;
    String mUrl;

    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url){
        mMagnitude = magnitude;
        mLocation = location;
        mtimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mtimeInMilliseconds;
    }

    public String getUrl(){
        return mUrl;
    }
}
