package com.gandan.android.retrofitpractice;

import retrofit2.http.GET;

/**
 * Created by XPS on 2018-01-18.
 */

public interface CRUDService {
    //"GET"(Read) 일 때 할 일을 적으면 된다.
    @GET("posts/{id}/comments")

}
