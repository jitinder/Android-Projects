package com.example.android.tourguideappproject;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Sidak Pasricha on 01/01/2018.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

    Context mContext;

    public CategoryAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ShoppingFragment();
            case 1:
                return new DiningFragment();
            case 2:
                return new EntertainmentFragment();
            case 3:
                return new EventsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.shopping_name);
            case 1:
                return mContext.getString(R.string.dining_name);
            case 2:
                return mContext.getString(R.string.entertainment_name);
            case 3:
                return mContext.getString(R.string.events_name);
            default:
                return null;
        }
    }
}
