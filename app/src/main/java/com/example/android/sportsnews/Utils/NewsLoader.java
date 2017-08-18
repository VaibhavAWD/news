package com.example.android.sportsnews.Utils;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * This {@link NewsLoader} class uses {@link AsyncTaskLoader} to perform background tasks and
 * obtain results
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        // escape early if url is null
        if (mUrl == null) {
            return null;
        }

        return Network.fetchNews(mUrl);
    }
}
