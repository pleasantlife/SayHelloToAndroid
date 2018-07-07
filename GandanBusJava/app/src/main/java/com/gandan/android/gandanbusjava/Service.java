package com.gandan.android.gandanbusjava;

import com.gandan.android.gandanbusjava.model.Response;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("buslocationservice")
    Observable<Response> getLiveBus(@Query(value="nickname", encoded = false) String serviceKey, @Query("routeId") long routeId);
}
