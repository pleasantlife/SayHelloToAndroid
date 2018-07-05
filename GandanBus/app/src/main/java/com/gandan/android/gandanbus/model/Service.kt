package com.gandan.android.gandanbus.model


import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("buslocationservice")
    fun getLiveBus(@Query("serviceKey") serviceKey : String, @Query("routeId") routeId : Long) : Observable<Response>

}