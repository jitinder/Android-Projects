package com.example.android.tourguideappproject;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sidak Pasricha on 31/12/2017.
 */

public class LocationAdapter extends ArrayAdapter<Location> {

    int bgColor = R.color.colorAccent;

    public LocationAdapter(Activity context, ArrayList<Location> locations, int color){
        super(context, 0, locations);
        bgColor = color;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Location currentLocation = getItem(position);

        LinearLayout linearLayout = (LinearLayout) listItemView.findViewById(R.id.linear_layout);
        int colorInt = ContextCompat.getColor(getContext(),bgColor);
        linearLayout.setBackgroundColor(colorInt);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.location_name);
        TextView descTextView = (TextView) listItemView.findViewById(R.id.location_description);
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_view);

        nameTextView.setText(currentLocation.getName());
        descTextView.setText(currentLocation.getDescription());

        if(currentLocation.getImageResourceId() != 0){
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(currentLocation.getImageResourceId());
        } else {
            imageView.setVisibility(View.GONE);
        }

        return listItemView;
    }
}
