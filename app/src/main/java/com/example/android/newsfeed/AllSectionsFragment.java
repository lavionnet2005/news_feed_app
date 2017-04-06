package com.example.android.newsfeed;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllSectionsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>>{

    private static String all_sections_url = "http://content.guardianapis.com/search?api-key=test" ;
    private ArrayList<News> results = new ArrayList<>();
    private View rootView;
    private TextView mEmptyStateTextView;
    private NewsAdapter adapter;
    private ListView listView;
    private static final int LOADER_ID = 4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.news_feed_grid_view, container, false);

        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty);
        listView = (ListView) rootView.findViewById(R.id.list);
        adapter = new NewsAdapter(getActivity(), results);

        listView.setAdapter(adapter);

        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            LoaderManager loaderManager = getActivity().getLoaderManager();
            loaderManager.initLoader(LOADER_ID, null, AllSectionsFragment.this);
        } else {
            View loadingIndicator = rootView.findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            listView.setEmptyView(mEmptyStateTextView);
            mEmptyStateTextView.setText(R.string.no_internet);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                News news = results.get(position);

                String url = news.getUrl();
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        return rootView;
    }


    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        Uri baseUri = Uri.parse(all_sections_url);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        Date date = new Date();
        String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
        uriBuilder.appendQueryParameter("from-date", modifiedDate);

        return new ListingLoader(getContext(), uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        View loadingIndicator = rootView.findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        adapter.clear();
        if (data != null && !data.isEmpty()) {
            results.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {

    }

}


