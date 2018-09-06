package com.example.android.newsappproject;

import android.content.AsyncTaskLoader;
import android.content.Context;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sidak Pasricha on 20-Jul-18.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String query = "";

    public NewsLoader(Context context, String url) {
        super(context);
        query = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if(query.equals("")){
            return null;
        }

        List<News> news = new ArrayList<>();
        try {
            news = DataFetcher.parseJSON(query);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return news;
    }
}
