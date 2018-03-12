package com.example.android.booklist;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by user on 15-06-2017.
 */

public class BookLoader extends AsyncTaskLoader<ArrayList<Book>> {

    private String mUrl;

    public BookLoader(Context context,String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public ArrayList<Book> loadInBackground() {
        if (mUrl==null){
            return null;
        }
        ArrayList<Book> books = QueryUtils.fetchBooksData(mUrl);
        return books;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
