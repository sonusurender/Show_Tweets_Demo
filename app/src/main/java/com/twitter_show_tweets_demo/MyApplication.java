package com.twitter_show_tweets_demo;

import android.app.Application;
import android.util.Log;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

/**
 * Created by sonu on 22/01/18.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //config twitter with API Key and Secret Key
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig("CONSUMER_KEY", "CONSUMER_SECRET"))
                .debug(true)
                .build();

        //initialize twitter
        Twitter.initialize(config);
    }
}
