package com.example.android.sportsnews;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.sportsnews.Utils.News;
import com.example.android.sportsnews.Utils.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<News>> {

    private RecyclerView mNewsList;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private TextView mEmptyView;

    private int NEWS_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewsList = (RecyclerView) findViewById(R.id.news_list);

        mNewsList.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mNewsList.setLayoutManager(mLayoutManager);

        mAdapter = new NewsAdapter(new ArrayList<News>());
        mNewsList.setAdapter(mAdapter);

        mEmptyView = (TextView) findViewById(R.id.empty_view);

        LoaderManager loaderManager = getLoaderManager();

        // check connectivity
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            mEmptyView.setVisibility(View.VISIBLE);
            mEmptyView.setText(R.string.error_connection);
        }

    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> newsList) {

    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {

    }
}
