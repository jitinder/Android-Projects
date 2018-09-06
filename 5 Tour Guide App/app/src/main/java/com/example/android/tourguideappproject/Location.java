package com.example.android.tourguideappproject;


/**
 * Created by Sidak Pasricha on 31/12/2017.
 */

public class Location {

    private String mName;
    private String mDescription;
    private String mLocationText;
    private int mImageResourceId = 0;

    public Location(String name, String description, int imageResourceId){
        mName = name;
        mDescription = description;
        mImageResourceId = imageResourceId;
    }

    public Location(String name, String description, String locationText, int imageResourceId){
        mName = name;
        mDescription = description;
        mLocationText = locationText;
        mImageResourceId = imageResourceId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getLocationText() {
        return mLocationText;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }
}
