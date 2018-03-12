package com.example.android.booklist;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Book>>{

    public static final String LOG_TAG = MainActivity.class.getName();

    private BookAdapter mAdapter;

    private static final int Book_LOADER_ID = 1;

    private TextView mEmptyStateTextView;

    private static String BOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);


        ImageView imageView = (ImageView)findViewById(R.id.search);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.edit_text);
                String input = editText.getText().toString();
                editText.setEnabled(false);
                editText.setEnabled(true);
                View loadingIndicator = findViewById(R.id.loading_indicator);
                loadingIndicator.setVisibility(View.VISIBLE);
                BOOK_REQUEST_URL = BOOK_REQUEST_URL+input+"&orderBy=newest&maxResults=10";
                ListView bookListView = (ListView) findViewById(R.id.list);
                //ArrayList<Book> books = QueryUtils.fetchBooksData(BOOK_REQUEST_URL);
                mAdapter = new BookAdapter(MainActivity.this,new ArrayList<Book>());
                bookListView.setAdapter(mAdapter);
                mEmptyStateTextView = (TextView)findViewById(R.id.empty_view);
                bookListView.setEmptyView(mEmptyStateTextView);
                bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        // Find the current earthquake that was clicked on
                        Book currentBook =  mAdapter.getItem(position);

                        // Convert the String URL into a URI object (to pass into the Intent constructor)
                        Uri earthquakeUri = Uri.parse(currentBook.getUrl());

                        // Create a new intent to view the earthquake URI
                        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                        // Send the intent to launch a new activity
                        startActivity(websiteIntent);
                    }
                });
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connMgr.getActiveNetworkInfo();
                if(info!=null&&info.isConnected()) {

                    // Start the AsyncTask to fetch the earthquake data
                    LoaderManager loaderManager = getLoaderManager();
                    loaderManager.initLoader(Book_LOADER_ID, null, MainActivity.this);
                    //loaderManager.restartLoader(Book_LOADER_ID, null, MainActivity.this);
                }
                else {
                    loadingIndicator = findViewById(R.id.loading_indicator);
                    loadingIndicator.setVisibility(View.GONE);
                    mEmptyStateTextView.setText(R.string.no_internet);
                }
               // BookAsyncTask task = new BookAsyncTask();
                //task.execute(BOOK_REQUEST_URL);
            }
        });
        ImageView home = (ImageView)findViewById(R.id.image);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.clear();
                mEmptyStateTextView.setVisibility(View.GONE);
            }
        });



    }

    @Override
    public Loader<ArrayList<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(this,BOOK_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Book>> loader, ArrayList<Book> data) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        mEmptyStateTextView.setText(R.string.no_book);
        mAdapter.clear();
        if (data!=null&&!data.isEmpty()){
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Book>> loader) {
        mAdapter.clear();
    }

    /*private class BookAsyncTask extends AsyncTask<String,Void,ArrayList<Book>>{

        @Override
        protected ArrayList<Book> doInBackground(String... urls) {
            if (urls.length < 1|| urls[0]==null){
                return null;
            }
            ArrayList<Book> books = QueryUtils.fetchBooksData(urls[0]);
            return books;
        }

        @Override
        protected void onPostExecute(ArrayList<Book> books) {

            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (books != null && !books.isEmpty()) {
                mAdapter.addAll(books);
            }
        }
    }*/


}
