package com.example.moham.twitter_ai;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.Uri;
import java.util.ArrayList;
import java.util.List;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by moham on 14-May-18.
 */

public class TweetLoader extends AsyncTaskLoader<List<tweets>> {
    private ArrayList<tweets> tweetsList = new ArrayList<>();


    TweetLoader(Context context) {

        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Override
    public List<tweets> loadInBackground() {

        try {
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
                tweets tweet =
                        new tweets(profileImage,
                                s.getUser().getName(),
                                "@" + s.getUser().getScreenName(),
                                s.getText(), s.getCreatedAt().getTime());
                tweetsList.add(tweet);

            }


        } catch (TwitterException e) {
            e.printStackTrace();
        }

        return tweetsList;

    }


}

