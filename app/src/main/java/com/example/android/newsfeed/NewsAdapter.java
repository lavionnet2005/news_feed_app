package com.example.android.newsfeed;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter {


    public NewsAdapter(Activity activity, ArrayList<News> news) {
        super(activity, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listView = convertView;
        if(listView==null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        News news = (News) getItem(position);

        TextView title = (TextView) listView.findViewById(R.id.title);
        title.setText(news.getTitle());

        return listView;
    }
}
