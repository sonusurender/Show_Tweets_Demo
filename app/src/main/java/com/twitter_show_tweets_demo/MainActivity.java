package com.twitter_show_tweets_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthException;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.CompactTweetView;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find the id of main layout
        mainLayout = findViewById(R.id.main_layout);

        //find id of TweetView
        TweetView tweetView = findViewById(R.id.tweet_view);
        //set callback action to TweetView to listen favorite event
        tweetView.setOnActionCallback(actionCallback);

        //find id of CompactTweetView
        CompactTweetView compactTweetView = findViewById(R.id.compact_tweet_view);
        //set callback action to CompactTweetView to listen favorite event
        compactTweetView.setOnActionCallback(actionCallback);

        //load runtime Tweet
        loadTweet();
    }

    private void loadTweet() {
        //use any TweetID
        final long tweetId = 952933941702545408L;


        //start loading tweet and pass TweetID
        TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                //create TweetView or CompactTweetView with "result.data"
                final TweetView tweetView = new TweetView(MainActivity.this, result.data,
                        R.style.tw__TweetDarkWithActionsStyle);
                //set action callback
                tweetView.setOnActionCallback(actionCallback);
                //finally add created Tweet to LinearLayout
                mainLayout.addView(tweetView);
            }

            @Override
            public void failure(TwitterException exception) {
                //show toast if some exception occurs
                Toast.makeText(MainActivity.this, "Failed to show Tweet.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // launch the login activity when a guest user tries to favorite a Tweet
    final Callback<Tweet> actionCallback = new Callback<Tweet>() {
        @Override
        public void success(Result<Tweet> result) {
            // Intentionally blank
        }

        @Override
        public void failure(TwitterException exception) {
            if (exception instanceof TwitterAuthException) {
                Toast.makeText(MainActivity.this, "User is guest.", Toast.LENGTH_SHORT).show();
                //launch login activity from here
                //ask user to login
            }
        }
    };
}
