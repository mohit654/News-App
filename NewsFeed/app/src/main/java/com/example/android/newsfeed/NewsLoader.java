package com.example.android.newsfeed;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by user on 05-07-2017.
 */

public class NewsLoader extends AsyncTaskLoader<ArrayList<News>> {
    private String mUrl;
    public NewsLoader(Context context,String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public ArrayList<News> loadInBackground() {
        if (mUrl==null){
            return null;
        }
        ArrayList<News> news = QueryUtils.fetchNewsData(mUrl);
        return news;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
