package com.gandan.android.paginglibrarypractice;

import com.google.gson.annotations.SerializedName;

/**
 * Created by XPS on 2018-05-07.
 */

public class RedditPost {

    @SerializedName("name")
    private String name;

    @SerializedName("title")
    private String title;

    @SerializedName("score")
    private int score;

    @SerializedName("author")
    private String author;

    @SerializedName("subreddit")
    private String subreddit;

    @SerializedName("num_comments")
    private int num_comments;

    @SerializedName("created_utc")
    private long created;
}
