package com.example.moham.twitter_ai;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by moham on 27-Apr-18.
 */

public class tweetAdapter extends ArrayAdapter<tweets>{
    private ArrayList<tweets> mOriginalValues; // Original Values
    private ArrayList<tweets> items;
    tweets currentTweets;

    public tweetAdapter(@NonNull Context context, ArrayList<tweets> mProductArrayList) {
        super(context, 0, mProductArrayList);
        this.mOriginalValues = mProductArrayList;
        this.items = mProductArrayList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

         currentTweets = getItem(position);

        ImageView imageView = listItemView.findViewById(R.id.profile_image);

        Picasso.get().load(currentTweets.getPhoto()).into(imageView);

        TextView name = listItemView.findViewById(R.id.tv_name);
        name.setText(currentTweets.getName());

        TextView screenName = listItemView.findViewById(R.id.tv_screen_name);
        screenName.setText(currentTweets.getScreenName());

        TextView tweetText = listItemView.findViewById(R.id.tv_tweet_text);
        tweetText.setText(currentTweets.getTweet());

        Date dateObject = new Date(currentTweets.getTimeStamp());

        TextView dateView = listItemView.findViewById(R.id.date);

        String formattedDate = formatDate(dateObject);

        dateView.setText(formattedDate);


        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
}
