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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.sportsnews.Utils.News;
import com.example.android.sportsnews.Utils.NewsAdapter;
import com.example.android.sportsnews.Utils.NewsLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<News>> {

    private RecyclerView mNewsList;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private TextView mEmptyView;

    private int NEWS_LOADER_ID = 1;

    private static final String NEWS_REQUEST_URL =
            "http://content.guardianapis.com/search?q=debates&api-key=test";

    private List<News> mNews;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewsList = (RecyclerView) findViewById(R.id.news_list);

        mNewsList.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mNewsList.setLayoutManager(mLayoutManager);

        mNews = new ArrayList<>();

        mAdapter = new NewsAdapter(mNews);
        mNewsList.setAdapter(mAdapter);

        mEmptyView = (TextView) findViewById(R.id.empty_view);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_indicator);

        initLoader();
    }

    private void initLoader() {
        LoaderManager loaderManager = getLoaderManager();

        // check connectivity
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            mProgressBar.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
            mEmptyView.setText(R.string.error_connection);
        }
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, NEWS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> newsList) {
        mProgressBar.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        mNews.clear();

        if (newsList != null && !newsList.isEmpty()) {
            mNews = newsList;
            mAdapter = new NewsAdapter(mNews);
        } else {
            mEmptyView.setVisibility(View.VISIBLE);
            mEmptyView.setText(R.string.no_news_found);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mNews.clear();
    }
}
