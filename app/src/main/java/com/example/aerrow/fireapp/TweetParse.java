package com.example.aerrow.fireapp;

import twitter4j.ResponseList;
import twitter4j.Status;

public class TweetParse {
    //Response List.
    private ResponseList<Status> timeline;
    TweetParse(ResponseList<Status> timeline){
        this.timeline=timeline;
        //Call result cleaning method.
        this.parseTweets();
    }
    //Removes update tweets that are irrelevant to operation.
    private void parseTweets(){
        this.timeline.removeIf(temp -> temp.getText().toLowerCase().contains("update"));
    }
    //Get public tweets.
    ResponseList<twitter4j.Status> getParsedTimeline() {
        return this.timeline;
    }

}
