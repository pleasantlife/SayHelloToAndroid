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
 *  서버와 통신하기 위해 Retrofit과 Retrofit의 통신내역을 확인하기 위한 OkHttp의 HttpLoggingInterceptor를 사용한다.
 */

public class RetrofitInit {

    /**
     *  Vworld의 Url과 인증키가 필요하다.(인증키는 무료 발급, 일 최대 3만건 사용 가능)
     *  아래 인증키는 임의로 생성함.
     */
    public static String SERVER_URL ="http://api.vworld.kr/";
    public static String AUTH_KEY = "64A0A1A4-9948-32AA-9522-AAA78A0A26A0";

    /**
     *  Retrofit로 통신하는 동안 통신내역을 확인하기 위해 별도의 HttpLoggingInterceptor를 생성한다.
     */
    private HttpLoggingInterceptor setLoggingInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //아래와 같이 메세지를 로그로 남기도록 하면, 로그를 통해 통신내역을 확인할 수 있다.
                Log.d("okhttp", message+"");
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    /**
     *  OkHttpClient를 생성한 후, addInterceptor로 위에서 생성한 HttpLogginInterceptor를 추가한다.
     */
    OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(setLoggingInterceptor()).build();

    /**
     *  Retrofit을 생성하고, 통신을 위한 인터페이스와 연결한다.
     */
    Retrofit retrofit = new Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).client(httpClient).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

    VworldService vworldService = retrofit.create(VworldService.class);

}
