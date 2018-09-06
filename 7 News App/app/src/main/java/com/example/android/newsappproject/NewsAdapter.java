package com.example.android.newsappproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sidak Pasricha on 19-Jul-18.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View root = convertView;
        final News currentNews = getItem(position);

        if(root == null){
            root = LayoutInflater.from(getContext()).inflate(R.layout.news_card_layout, parent, false);
        }

        TextView newsTitle = (TextView) root.findViewById(R.id.news_title);
        TextView newsAuthor = (TextView) root.findViewById(R.id.news_author);
        TextView newsSection = (TextView) root.findViewById(R.id.news_section);
        TextView newsDate = (TextView) root.findViewById(R.id.news_date);

        if(currentNews != null){
            newsTitle.setText(currentNews.getTitle());
            newsAuthor.setText(currentNews.getAuthor());
            newsSection.setText(currentNews.getSection());
            newsDate.setText(currentNews.getDate());

            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(currentNews.getUrl()));
                    getContext().startActivity(i);
                }
            });
        }

        return root;
    }
}
