package com.gandan.android.vworldapipractice;

import com.gandan.android.vworldapipractice.Model.AddressQuery;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by XPS on 2018-02-28.
 */

public interface VworldService {

    @GET("req/search")
    Observable<AddressQuery> getAddress(@Query("key") String authKey, @Query("request") String search, @Query("type") String type, @Query("category") String category, @Query("format") String json, @Query("size") int size, @Query("query") String query);
}
