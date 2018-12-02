package com.example.aerrow.fireapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Iterator;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

public class Tweets extends AppCompatActivity {

    //Pull Tweets and put them on buttons.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweetlist);
        //Button selection function call here.
    }

    //Put tweets on buttons after grabbing them.
    private class setCallSelectionButtons extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            ConfigurationBuilder builder = new ConfigurationBuilder();
            //Authentication Block.
            builder.setOAuthConsumerKey("INSERT_KEY_HERE");
            builder.setOAuthConsumerSecret("INSERT_AUTHEN_KEY_HERE");
            AccessToken token = new AccessToken("ACCESS_TOKEN_GOES_HERE","ACCESS_TOKEN_PASS_GOES_HERE");
            //Call Twitter4J Library and button mapping.
            Twitter twitter = new TwitterFactory(builder.build()).getInstance(token);
            try {
                ResponseList<twitter4j.Status> tweets;
                tweets = new TweetParse(twitter.getUserTimeline("INCIDENT")).getParsedTimeline();
                runOnUiThread(() -> {
                    ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
                    ArrayList<View> buttons = viewGroup.getTouchables();
                    Iterator<twitter4j.Status> iter = tweets.iterator();
                    for (View view : buttons) {
                        if(view instanceof Button)((Button) view).setText(iter.next().getText());
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
