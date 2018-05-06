package com.gandan.android.paginglibrarypractice;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by XPS on 2018-04-22.
 */

public interface RedditService {

    @GET("/r/{subreddit}/hot.json")
    Call<ListingResponse> getTop(@Path("subreddit") String subReddit, @Query("before") String before, @Query("limit") int limit);
}
