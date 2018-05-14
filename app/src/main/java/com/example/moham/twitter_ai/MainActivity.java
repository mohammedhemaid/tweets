package com.example.moham.twitter_ai;

import android.app.LoaderManager;
import android.content.Loader;
import android.net.Uri;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<tweets>> {
    private ArrayList<tweets> tweetsList = new ArrayList<>();
    private tweetAdapter mAdapter;
    private ProgressBar pb_Loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb_Loading = findViewById(R.id.pb_loading);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        mAdapter = new tweetAdapter(this, tweetsList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getLoaderManager().initLoader(1, null, this);
    }


    @Override
    public Loader<List<tweets>> onCreateLoader(int id, Bundle args) {

        TweetLoader loader;
        pb_Loading.setVisibility(View.VISIBLE);
        loader = new TweetLoader(this);
        return loader;

    }

    @Override
    public void onLoadFinished(Loader<List<tweets>> loader, List<tweets> tweet) {
        pb_Loading.setVisibility(View.GONE);
        mAdapter.setCardInfoList(tweet);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onLoaderReset(Loader<List<tweets>> loader) {

    }


}

