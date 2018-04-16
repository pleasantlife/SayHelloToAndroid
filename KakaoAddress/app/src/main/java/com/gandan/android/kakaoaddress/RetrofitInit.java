package com.gandan.android.kakaoaddress;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by XPS on 2018-04-14.
 */

public class RetrofitInit {

    //카카오 개발자 센터에서 앱을 등록하여 KEY를 받을 수 있다.
    //개발자 센터 사이트 (로컬) 주소 : https://developers.kakao.com/docs/restapi/local

    public static final String KAKAO_KEY = "c92f68c8c66104311b5927acb8beb88e";
    public static final String KAKAO_URL = "https://dapi.kakao.com/";


    private HttpLoggingInterceptor setLoggoingInterceptor(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("okHttp", message+"");
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return loggingInterceptor;
    }

    private OkHttpClient setOkHttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(setLoggoingInterceptor()).build();
        return okHttpClient;
    }



    Retrofit retrofit = new Retrofit.Builder().client(setOkHttpClient()).baseUrl(KAKAO_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();

    KakaoAddressService kakaoAddressService = retrofit.create(KakaoAddressService.class);


}
