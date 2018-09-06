package com.example.android.tourguideappproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {


    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list, container, false);


        final ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location(getString(R.string.qawaali_name),getString(R.string.qawaali_desc), R.drawable.qawaali_night));
        locations.add(new Location(getString(R.string.imagine_name),getString(R.string.imagine_desc), R.drawable.concert_stage));
        locations.add(new Location(getString(R.string.hamlet_name),getString(R.string.hamlet_desc), R.drawable.play_stage));
        locations.add(new Location(getString(R.string.ball_name),getString(R.string.ball_desc), R.drawable.ball_floor));


        LocationAdapter locationAdapter = new LocationAdapter(getActivity(), locations, R.color.colorEvents);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(locationAdapter);

        return rootView;
    }

}
