package com.gandan.android.paginglibrarypractice;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by XPS on 2018-04-22.
 */

public class RetrofitInit {

    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.reddit.com/").addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();

    RedditService redditService = retrofit.create(RedditService.class);
}
