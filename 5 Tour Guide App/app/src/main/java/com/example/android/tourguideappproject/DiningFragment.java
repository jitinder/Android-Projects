package com.example.android.tourguideappproject;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiningFragment extends Fragment {

    public DiningFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list, container, false);

        int restaurantIcon = R.drawable.ic_restaurant_black_24dp;

        final ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location(getString(R.string.bikanervala_name),
                getString(R.string.bikanervala_desc), getString(R.string.bikanervala_map), restaurantIcon));
        locations.add(new Location(getString(R.string.cyberhub_name),
                getString(R.string.cyberhub_desc),getString(R.string.cyberhub_map), restaurantIcon));
        locations.add(new Location(getString(R.string.horizon_name),
                getString(R.string.horizon_desc),getString(R.string.horizon_map), restaurantIcon));
        locations.add(new Location(getString(R.string.chinaclub_name),
                getString(R.string.chinaclub_desc),getString(R.string.chinaclub_map), restaurantIcon));
        locations.add(new Location(getString(R.string.sector_name),
                getString(R.string.sector_desc),getString(R.string.sector_map), restaurantIcon));
        locations.add(new Location(getString(R.string.kebab_name),
                getString(R.string.kebab_desc),getString(R.string.kebab_map), restaurantIcon));


        LocationAdapter locationAdapter = new LocationAdapter(getActivity(), locations, R.color.colorDining);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(locationAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showMap(locations.get(i).getLocationText());
            }
        });

        return rootView;
    }

    public void showMap(String geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getString(R.string.map_query) +geoLocation));
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
