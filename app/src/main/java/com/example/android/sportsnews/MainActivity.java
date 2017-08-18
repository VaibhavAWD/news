package com.example.android.sportsnews;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.sportsnews.Utils.News;
import com.example.android.sportsnews.Utils.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<News>> {

    private RecyclerView mNewsList;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private View mEmptyView;

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

        mEmptyView = findViewById(R.id.empty_view);

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
