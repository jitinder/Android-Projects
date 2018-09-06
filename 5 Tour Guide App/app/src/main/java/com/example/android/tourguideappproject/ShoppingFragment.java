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
public class ShoppingFragment extends Fragment {


    public ShoppingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list, container, false);

        int defaultShoppingIcon = R.drawable.ic_shopping_cart_black_24dp;

        final ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location(getString(R.string.ambience_name),
                getString(R.string.ambience_desc), getString(R.string.ambience_map), defaultShoppingIcon));
        locations.add(new Location(getString(R.string.mgf_name),
                getString(R.string.mgf_desc), getString(R.string.mgf_map), defaultShoppingIcon));
        locations.add(new Location(getString(R.string.dt_name),
                getString(R.string.dt_desc), getString(R.string.dt_map), defaultShoppingIcon));
        locations.add(new Location(getString(R.string.central_name),
                getString(R.string.central_desc), getString(R.string.central_map), defaultShoppingIcon));
        locations.add(new Location(getString(R.string.sahara_name),
                getString(R.string.sahara_desc), getString(R.string.sahara_map), defaultShoppingIcon));
        locations.add(new Location(getString(R.string.galleria_name),
                getString(R.string.galleria_desc), getString(R.string.galleria_map), defaultShoppingIcon));
        locations.add(new Location(getString(R.string.mall_name),
                getString(R.string.mall_desc), getString(R.string.mall_map), defaultShoppingIcon));

        LocationAdapter locationAdapter = new LocationAdapter(getActivity(), locations, R.color.colorShopping);
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
        intent.setData(Uri.parse(R.string.map_query +geoLocation));
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
