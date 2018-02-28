package com.gandan.android.vworldapipractice;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by XPS on 2018-02-28.
 */

public class RetrofitInit {

    public static String SERVER_URL ="http://api.vworld.kr/";
    public static String AUTH_KEY = "64F0C1E4-9948-32FD-9522-EFE78A0F26C0";

    private HttpLoggingInterceptor setLoggingInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("okhttp", message+"");
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(setLoggingInterceptor()).build();

    Retrofit retrofit = new Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).client(httpClient).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

    VworldService vworldService = retrofit.create(VworldService.class);

}
