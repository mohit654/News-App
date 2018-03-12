package com.example.android.newsfeed;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<News>>{

    public DrawerLayout mDrawerLayout;

    private int size = 10;

    private static final int NEWS_LOADER_ID = 1;

    public ActionBarDrawerToggle mToggle;

    public static final String LOG_TAG = MainActivity.class.getName();

    private NewsAdapter madapter;

    private View loadingIndicator;

    private TextView mEmptyStateTextView;

    private TextView mShowMore;

    private SwipeRefreshLayout mSwipeRefresh;

    private NavigationView navigationView;

    private static String NEWS_REQUEST_URL = "https://content.guardianapis.com/search?api-key=test&page-size=10&order-by=newest&show-fields=standfirst,starRating,headline,thumbnail,short-url,lastModified,byline";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);

        mShowMore = (TextView) findViewById(R.id.show);

        loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listView = (ListView)findViewById(R.id.list);
        madapter = new NewsAdapter(this,new ArrayList<News>());
        listView.setAdapter(madapter);
        mEmptyStateTextView = (TextView)findViewById(R.id.empty_view);
        listView.setEmptyView(mEmptyStateTextView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = (News) madapter.getItem(position);
                Uri newsUri = Uri.parse(news.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW,newsUri);
                startActivity(websiteIntent);
            }
        });
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.isChecked()){
                    item.setChecked(false);
                }
                else {
                    item.setChecked(true);
                }
                mDrawerLayout.closeDrawers();
                size = 10;
                mShowMore.setVisibility(View.VISIBLE);
                switch (item.getItemId()){
                    case R.id.world:
                        NEWS_REQUEST_URL = "http://content.guardianapis.com/search?api-key=f51fa87d-8a55-4fc1-b552-8fa6eb98dee4&section=world";
                        performTask();
                        setTitle("World News");
                        return true;
                    case R.id.politics:
                        NEWS_REQUEST_URL = "http://content.guardianapis.com/search?api-key=f51fa87d-8a55-4fc1-b552-8fa6eb98dee4&section=politics";
                        performTask();
                        setTitle("Politics News");
                        return true;
                    case R.id.education:
                        NEWS_REQUEST_URL = "http://content.guardianapis.com/search?api-key=f51fa87d-8a55-4fc1-b552-8fa6eb98dee4&section=education";
                        performTask();
                        setTitle("Education News");
                        return true;
                    case R.id.sports:
                        NEWS_REQUEST_URL = "http://content.guardianapis.com/search?api-key=f51fa87d-8a55-4fc1-b552-8fa6eb98dee4&section=sport";
                        performTask();
                        setTitle("Sports News");
                        return true;
                    case R.id.business:
                        NEWS_REQUEST_URL = "http://content.guardianapis.com/search?api-key=f51fa87d-8a55-4fc1-b552-8fa6eb98dee4&section=business";
                        performTask();
                        setTitle("Business News");
                        return true;
                    case R.id.technology:
                        NEWS_REQUEST_URL = "http://content.guardianapis.com/search?api-key=f51fa87d-8a55-4fc1-b552-8fa6eb98dee4&section=technology";
                        performTask();
                        setTitle("Technology News");
                        return true;
                    case R.id.opinion:
                        NEWS_REQUEST_URL = "http://content.guardianapis.com/search?api-key=f51fa87d-8a55-4fc1-b552-8fa6eb98dee4&section=commentisfree";
                        performTask();
                        setTitle("Opinion News");
                        return true;
                    case R.id.travel:
                        NEWS_REQUEST_URL = "http://content.guardianapis.com/search?api-key=f51fa87d-8a55-4fc1-b552-8fa6eb98dee4&section=travel";
                        performTask();
                        setTitle("Travel News");
                        return true;
                    case R.id.culture:
                        NEWS_REQUEST_URL = "http://content.guardianapis.com/search?api-key=f51fa87d-8a55-4fc1-b552-8fa6eb98dee4&section=culture";
                        performTask();
                        setTitle("Culture News");
                        return true;
                    case R.id.football:
                        NEWS_REQUEST_URL = "http://content.guardianapis.com/search?api-key=f51fa87d-8a55-4fc1-b552-8fa6eb98dee4&section=football";
                        performTask();
                        setTitle("Football News");
                        return true;
                    case R.id.fashion:
                        NEWS_REQUEST_URL = "http://content.guardianapis.com/search?api-key=f51fa87d-8a55-4fc1-b552-8fa6eb98dee4&section=fashion";
                        performTask();
                        setTitle("Fashion News");
                        return true;
                    case R.id.environment:
                        NEWS_REQUEST_URL = "http://content.guardianapis.com/search?api-key=f51fa87d-8a55-4fc1-b552-8fa6eb98dee4&section=environment";
                        performTask();
                        setTitle("Environment News");
                        return true;
                    case R.id.lifestyle:
                        NEWS_REQUEST_URL = "http://content.guardianapis.com/search?api-key=f51fa87d-8a55-4fc1-b552-8fa6eb98dee4&section=lifeandstyle";
                        performTask();
                        setTitle("Lifestyle News");
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });
        mShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (size < 30) {
                    size = size + 5;
                    performTask();
                }
                else {
                    mShowMore.setVisibility(View.GONE);
                }
            }
        });
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                performTask();
                mSwipeRefresh.setRefreshing(true);
                mSwipeRefresh.post(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefresh.setRefreshing(false);
                        performTask();
                    }
                });
            }
        });
        performTask();
    }
    private void performTask(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connMgr.getActiveNetworkInfo();
        if(info!=null&&info.isConnected()) {

            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, MainActivity.this);
            loaderManager.restartLoader(NEWS_LOADER_ID, null, MainActivity.this);
        }
        else {
            loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        int id= item.getItemId();
        if (id == R.id.action_home){
            NEWS_REQUEST_URL = "http://content.guardianapis.com/search?api-key=f51fa87d-8a55-4fc1-b552-8fa6eb98dee4&page-size=10&order-by=newest&show-fields=standfirst,starRating,headline,thumbnail,short-url,lastModified,byline";
            setTitle("News Snips");
            performTask();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<ArrayList<News>> onCreateLoader(int id, Bundle args) {
        loadingIndicator.setVisibility(View.VISIBLE);
        Uri baseUri = Uri.parse(NEWS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("format", "json");
        uriBuilder.appendQueryParameter("page-size",String.valueOf(size));
        uriBuilder.appendQueryParameter("show-fields", "standfirst,starRating,headline,thumbnail,short-url,bodyText,lastModified,byline");
        return new NewsLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<News>> loader, ArrayList<News> data) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        mEmptyStateTextView.setText(R.string.no_news);
        madapter.clear();
        if (data!=null&&!data.isEmpty()){
            madapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<News>> loader) {
        madapter.clear();
    }

    /*private class NewsAsyncTask extends AsyncTask<String,Void,ArrayList<News>>{

        @Override
        protected ArrayList<News> doInBackground(String... url) {
            Uri baseUri = Uri.parse(url[0]);
            Uri.Builder uriBuilder = baseUri.buildUpon();
            uriBuilder.appendQueryParameter("format", "json");
            uriBuilder.appendQueryParameter("page-size","10");
            uriBuilder.appendQueryParameter("show-fields", "standfirst,starRating,headline,thumbnail,short-url,bodyText,lastModified,byline");
            ArrayList<News> news = QueryUtils.fetchNewsData(uriBuilder.toString());
            return news;
        }

        @Override
        protected void onPostExecute(ArrayList<News> newses) {
            madapter.clear();
            if (newses!=null&&!newses.isEmpty()){
                madapter.addAll(newses);
            }
        }
    }*/

}
