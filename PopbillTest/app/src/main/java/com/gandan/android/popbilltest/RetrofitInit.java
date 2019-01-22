package com.gandan.android.popbilltest;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInit {

    public static final String BASE_URL = "https://popbill_test.linkhub.co.kr/KakaoTalk/";

    private String temp = "?TG=PLUSFRIEND";

    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

    RetrofitService service = retrofit.create(RetrofitService.class);


}
