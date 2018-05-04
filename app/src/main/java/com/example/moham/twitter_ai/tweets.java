package com.example.moham.twitter_ai;

import android.net.Uri;

/**
 * Created by moham on 27-Apr-18.
 */

public class tweets {

    Uri photo;
    String name;
    String screenName;
    String tweet;
    long timeStamp;

    public tweets(Uri photo, String name, String screenName, String tweet, long timeStamp) {
        this.photo = photo;
        this.name = name;
        this.screenName = screenName;
        this.tweet = tweet;
        this.timeStamp = timeStamp;
    }

    public String getName() {
        return name;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getTweet() {
        return tweet;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public Uri getPhoto() {
        return photo;
    }
}
