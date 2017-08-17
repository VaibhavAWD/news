package com.example.android.sportsnews.Utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sportsnews.R;

import java.util.List;

/**
 * This {@link NewsAdapter} class uses {@link android.support.v7.widget.RecyclerView.Adapter} using
 * {@link NewsAdapter.ViewHolder} class as object type to populate the items in the list
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<News> mNewsList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mNewsTitle;
        public TextView mNewsSectionName;
        public ViewHolder(View view) {
            super(view);
            mNewsTitle = view.findViewById(R.id.news_title);
            mNewsSectionName = view.findViewById(R.id.section_name);
        }
    }

    public NewsAdapter(List<News> newsList){
        mNewsList = newsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News news = mNewsList.get(position);

        holder.mNewsTitle.setText(news.getNewsTitle());
        holder.mNewsSectionName.setText(news.getNewsSectionName());
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }
}
