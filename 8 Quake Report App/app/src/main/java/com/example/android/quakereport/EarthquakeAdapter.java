package com.example.android.quakereport;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sidak Pasricha on 23/01/2018.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    private String formatDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return simpleDateFormat.format(date);
    }

    private String formatTime(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm a");
        return simpleDateFormat.format(date);
    }

    private String[] splitLocation(String location){
        String[] toReturn = new String[2];

        int indexOfOf = location.indexOf("of");
        if(indexOfOf == -1){
            toReturn[0] = "Near the";
            toReturn[1] = location;
        } else {
            toReturn[0] = location.substring(0, indexOfOf+2);
            toReturn[1] = location.substring(indexOfOf+2);
        }

        return toReturn;
    }

    private String formatMagnitude(double magnitude){
        DecimalFormat formatter = new DecimalFormat("0.0");
        String toReturn = formatter.format(magnitude);

        return toReturn;
    }

    private int getMagnitudeColor(double magnitude){
        int mag = (int) magnitude;
        int colorResourceID;
        switch (mag){
            case 0:
            case 1:
                colorResourceID = R.color.magnitude1;
                break;
            case 2:
                colorResourceID = R.color.magnitude2;
                break;
            case 3:
                colorResourceID = R.color.magnitude3;
                break;
            case 4:
                colorResourceID = R.color.magnitude4;
                break;
            case 5:
                colorResourceID = R.color.magnitude5;
                break;
            case 6:
                colorResourceID = R.color.magnitude6;
                break;
            case 7:
                colorResourceID = R.color.magnitude7;
                break;
            case 8:
                colorResourceID = R.color.magnitude8;
                break;
            case 9:
                colorResourceID = R.color.magnitude9;
                break;
            default:
                colorResourceID = R.color.magnitude10plus;
                break;

        }
        return ContextCompat.getColor(getContext(), colorResourceID);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        Earthquake earthquake = getItem(position);

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView eMag = listItemView.findViewById(R.id.emagnitude);
        TextView eDirection = listItemView.findViewById(R.id.edirection);
        TextView eCity = listItemView.findViewById(R.id.ecity);
        TextView eDate = listItemView.findViewById(R.id.edate);
        TextView eTime = listItemView.findViewById(R.id.etime);

        if(earthquake != null) {
            // Set the proper background color on the magnitude circle.
            // Fetch the background from the TextView, which is a GradientDrawable.
            GradientDrawable magnitudeCircle = (GradientDrawable) eMag.getBackground();

            // Get the appropriate background color based on the current earthquake magnitude
            int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());

            // Set the color on the magnitude circle
            magnitudeCircle.setColor(magnitudeColor);

            Date dateObject = new Date(earthquake.getTimeInMilliseconds());
            String date = formatDate(dateObject);
            String time = formatTime(dateObject);
            final String url = earthquake.getUrl();

            String[] locationData = splitLocation(earthquake.getLocation());
            String magnitude = formatMagnitude(earthquake.getMagnitude());

            eMag.setText(magnitude);
            eDirection.setText(locationData[0]);
            eCity.setText(locationData[1].trim());
            eDate.setText(date);
            eTime.setText(time);

        }


        return listItemView;
    }
}
