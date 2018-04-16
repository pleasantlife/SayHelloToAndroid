package com.gandan.android.naveraddresspractice;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by XPS on 2018-04-16.
 */

public class RetrofitInit {





    private HttpLoggingInterceptor loggingInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("okhttp", message+"");
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor()).build();

    Retrofit retrofit = new Retrofit.Builder().baseUrl().addCallAdapterFactory().addConverterFactory(GsonConverterFactory.create()).
}
