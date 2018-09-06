package com.example.android.newsappproject;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>{

    NewsAdapter newsAdapter;
    ProgressBar progressBar;
    TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        emptyView = (TextView) findViewById(R.id.empty_view);

        listView.setEmptyView(emptyView);

        if(!hasInternet()){
            progressBar.setVisibility(View.GONE);
            emptyView.setText(R.string.no_internet);
        }

        newsAdapter = new NewsAdapter(this, new ArrayList<News>());
        listView.setAdapter(newsAdapter);
        getLoaderManager().initLoader(0, null, this);
    }

    private boolean hasInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null || !networkInfo.isConnected()){
            return false;
        }
        return true;
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, "https://content.guardianapis.com/search?api-key=c794cb41-a2f4-4bdb-8d7b-37386bad5f08");
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        newsAdapter.clear();
        progressBar.setVisibility(View.GONE);

        if(data != null && !data.isEmpty()){
            newsAdapter.addAll(data);
            emptyView.setText("");
        } else {
            emptyView.setText(R.string.no_data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        loader.reset();
    }
}
