package com.example.android.sportsnews.Utils;

/**
 * This class {@link News} is used to store the information of the various news items
 */

public class News {
    private String mNewsTitle;
    private String mNewsSectionName;
    private String mNewsLink;

    public News(String mNewsTitle, String mNewsSectionName, String mNewsLink) {
        this.mNewsTitle = mNewsTitle;
        this.mNewsSectionName = mNewsSectionName;
        this.mNewsLink = mNewsLink;
    }

    public String getNewsTitle() {
        return mNewsTitle;
    }

    public String getNewsSectionName() {
        return mNewsSectionName;
    }

    public String getNewsLink() {
        return mNewsLink;
    }
}
