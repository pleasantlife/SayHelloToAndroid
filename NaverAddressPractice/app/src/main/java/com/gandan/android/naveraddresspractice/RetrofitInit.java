package com.gandan.android.naveraddresspractice;

import android.util.Log;

import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.gandan.android.naveraddresspractice.MainActivity.NAVER_URL;

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

    Retrofit retrofit = new Retrofit.Builder().baseUrl(NAVER_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(client).addConverterFactory(GsonConverterFactory.create()).build();

    AddressService addressService = retrofit.create(AddressService.class);
}
