package com.example.moham.twitter_ai;

import android.net.Uri;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import twitter4j.MediaEntity;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class MainActivity extends AppCompatActivity {
    private ArrayList<tweets> tweetsList = new ArrayList<>();
    private tweetAdapter mAdapter;
    private ProgressBar pb_Loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb_Loading = findViewById(R.id.pb_loading);
        pb_Loading.setVisibility(View.VISIBLE);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        mAdapter = new tweetAdapter(this, tweetsList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new TweetsAsyncTask().execute();
    }

        void gettweets () throws TwitterException {

            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true);
            cb.setOAuthConsumerKey("6PP9Qhwz81mXl00IZu9fxsXmm");
            cb.setOAuthConsumerSecret("Ww8lzNjk4UQRxKzMip3Bk5qVnZgbnv0iZ2UzOnGFkfSKWEXczh");
            cb.setOAuthAccessToken("3417793145-YsaGPrvzAaAfuu06Kc4nMI1mBA5kY6C0bWPsPYc");
            cb.setOAuthAccessTokenSecret("S0tbuqLeP8uRiDXsOfBtJxZlrCwVufsRPrVClrBRghfa0");

            Twitter twitter = new TwitterFactory(cb.build()).getInstance();
            final Paging paging = new Paging();
            paging.count(200);
            List<Status> status = twitter.getHomeTimeline(paging);
            for (Status s : status) {
                    Uri profileImage = Uri.parse(s.getUser().getProfileImageURL());
                    tweets tw =
                            new tweets(profileImage,
                                    s.getUser().getName(),
                                    "@" + s.getUser().getScreenName(),
                                    s.getText(), s.getCreatedAt().getTime());

                    tweetsList.add(tw);

                }

            }


      private class TweetsAsyncTask extends AsyncTask<Void,Void,Void>{

          @Override
          protected Void doInBackground(Void... voids) {
              try {
                  gettweets();
              }catch (TwitterException e){
                  e.printStackTrace();
              }
              return null;
          }

          @Override
          protected void onPostExecute(Void aVoid) {
              mAdapter.notifyDataSetChanged();
              pb_Loading.setVisibility(View.GONE);
          }
      }


    }



