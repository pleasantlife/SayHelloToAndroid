package com.gandan.android.retrofitpractice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by XPS on 2018-01-18.
 */

public interface CRUDService {
    //1. "GET"(Read) 일 때 할 일을 적으면 된다.
    //1-1. ServerURL/posts/를 하면 출력되는 json 데이터를 모두 받아온다.
    @GET("posts/")
    Call<List<PostInfo>> getPostList();

}
