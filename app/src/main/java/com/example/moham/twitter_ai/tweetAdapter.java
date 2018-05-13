package com.example.moham.twitter_ai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Edited by moham on 13-May-18.
 */

public class tweetAdapter extends RecyclerView.Adapter<tweetAdapter.TweetViewHolder> {

    private final LayoutInflater mInflater;
    private List<tweets> mTweets;

    tweetAdapter(Context context,List<tweets> mTweets) {
        mInflater = LayoutInflater.from(context);
        this.mTweets = mTweets;
    }

    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item, parent, false);
        return new TweetViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TweetViewHolder holder, int position) {

        tweets currentTweets = mTweets.get(position);
        Picasso.get().load(currentTweets.getPhoto()).into(holder.photo);
        holder.name.setText(currentTweets.getName());
        holder.screenName.setText(currentTweets.getScreenName());
        holder.tweetText.setText(currentTweets.getTweet());

        Date dateObject = new Date(currentTweets.getTimeStamp());
        String formattedDate = formatDate(dateObject);
        holder.dateView.setText(formattedDate);

    }
    @Override
    public int getItemCount() {
            return mTweets.size();
    }

    class TweetViewHolder extends RecyclerView.ViewHolder {
        private ImageView photo;
        private TextView name;
        private TextView screenName;
        private TextView tweetText;
        private TextView dateView;

        private TweetViewHolder(View listItemView) {
            super(listItemView);
            photo = listItemView.findViewById(R.id.profile_image);
            name = listItemView.findViewById(R.id.tv_name);
            screenName = listItemView.findViewById(R.id.tv_screen_name);
            tweetText = listItemView.findViewById(R.id.tv_tweet_text);
            dateView = listItemView.findViewById(R.id.date);

        }
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
}
