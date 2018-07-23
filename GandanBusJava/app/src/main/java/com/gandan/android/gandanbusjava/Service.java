package com.gandan.android.gandanbusjava;

import com.gandan.android.gandanbusjava.model.Response;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryName;
import retrofit2.http.Url;

public interface Service {

    @GET("buslocationservice")
    Observable<Response> getLiveBus(@Query("serviceKey") String serviceKey, @Query("routeId") long routeId);

    @GET
    Observable<ResponseBody> getRoutes (@Url String url);

    @GET
    Observable<ResponseBody> getRouteLines (@Url String url);

    @GET
    Observable<ResponseBody> getRouteStation (@Url String url);
}
