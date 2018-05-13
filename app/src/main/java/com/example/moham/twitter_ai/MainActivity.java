package com.example.moham.twitter_ai;

import android.net.Uri;

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
        mAdapter = new tweetAdapter(this,tweetsList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        gettweets();
    }

    void gettweets() {

        String url = "https://mohammedhemaid.000webhostapp.com/jsonTest.json";

        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {

                try {

                    JSONObject object = new JSONObject(response);

                    JSONArray array = object.getJSONArray("tweets");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject currentobject = array.getJSONObject(i);
                        String tweetText = currentobject.getString("text");
                        long date = currentobject.getLong("timestamp_ms");

                        JSONObject user = currentobject.getJSONObject("user");
                        String name = user.getString("name");
                        String screen_name = user.getString("screen_name");
                        String photo = user.getString("profile_image_url_https");

                        Uri image = Uri.parse(photo);

                        tweets tw = new tweets(image, name, "@" + screen_name, tweetText, date);

                        tweetsList.add(tw);
                        mAdapter.notifyDataSetChanged();
                        pb_Loading.setVisibility(View.GONE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(this).add(postRequest);
    }

}


