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
public class EntertainmentFragment extends Fragment {

    public EntertainmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list, container, false);

        int playIcon = R.drawable.ic_local_play_black_24dp;
        int gameIcon = R.drawable.ic_videogame_asset_black_24dp;
        int trampolineIcon = R.drawable.ic_pages_black_24dp;
        int parkIcon = R.drawable.ic_nature_people_black_24dp;

        final ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location(getString(R.string.kod_name),
                getString(R.string.kod_desc), getString(R.string.kod_map), playIcon));
        locations.add(new Location(getString(R.string.smash_name),
                getString(R.string.smash_desc), getString(R.string.smash_map), gameIcon));
        locations.add(new Location(getString(R.string.skyjumper_name),
                getString(R.string.skyjumper_desc), getString(R.string.skyjumper_map), trampolineIcon));
        locations.add(new Location(getString(R.string.leisure_name),
                getString(R.string.leisure_desc), getString(R.string.leisure_map), parkIcon));


        LocationAdapter locationAdapter = new LocationAdapter(getActivity(), locations, R.color.colorEntertainment);
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
