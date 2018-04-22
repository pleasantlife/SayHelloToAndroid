package com.gandan.android.paginglibrarypractice;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by XPS on 2018-04-22.
 */

public interface GithubService {

    @GET("users")
    Observable<List<User>> getUsers(@Query("since") long userId, @Query("per_page") int perPage);
}
