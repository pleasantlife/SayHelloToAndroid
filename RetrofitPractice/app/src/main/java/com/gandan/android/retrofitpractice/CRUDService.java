package com.gandan.android.retrofitpractice;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CRUDService {
    //1. "GET"(Read) 일 때 할 일을 적으면 된다.
    //1-1. ServerURL/posts/를 하면 출력되는 json 데이터를 모두 받아온다.
    @GET("posts/")
    Call<List<PostInfo>> getPostList();

    //2. RestAPI 서버는 각 항목별로 url을 설정하는 것을 표준으로 삼는데, 레트로핏에서는 url에 맞춰 원하는 데이터만 가지고 올 수도 있다.
    //{} 사이에 필요한 변수를 집어넣도록 하면, 레트로핏에서 가변적으로 url을 생성하여, 데이터를 받아온다.
    @GET("posts/{id}")
    Call<PostInfo> getPost(@Path("id") int id);

    //3. RestAPI 서버에서 id이외에도 원하는 값을 통해 데이터를 가져올 수 있도록, Query 파라미터도 지원한다.
    @GET("posts/")
    Call<List<PostInfo>> getPostQuery(@Query("id") int id);

    @POST("posts/")
    Call<PostInfo> postPostList(@Body PostInfo postInfo);

    //4. RxJava2Adapter를 이용하여, Observable 객체를 등록하여 사용할 수 있다.
    @GET("posts/{id}/comments/")
    Observable<List<PostInfo>> getComments(@Path("id") int id);

    @GET("posts/{id}/comments/")
    Flowable<List<PostInfo>> getFlowableComments (@Path("id") int id);

    @POST("posts/")
    //Multipart Annotation은 file을 보내야할 떄 사용한다.
    //Formurlencoded 규격으로는 file을 보낼 수 없다.
    @Multipart
    Observable<String> multiPartPost(@Part("title") RequestBody title, @Part MultipartBody.Part bodyString);


    @POST("posts/")
    @FormUrlEncoded
    Observable<Void> logoutPost(@Field("title") String title);

    @POST("posts/")
    @FormUrlEncoded
    Completable logout(@Field("title") String title);

    @POST("posts/")
    @FormUrlEncoded
    Call<String> getError(@Field("title") String title);
}
