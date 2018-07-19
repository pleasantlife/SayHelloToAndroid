package com.gandan.android.gandanbusjava;

import java.text.SimpleDateFormat;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitInit {

    public static final String SERVER_URL = "http://openapi.gbis.go.kr/ws/rest/";

    public static final String SERVICE_KEY = "iha9k%2Fpy8ZJHW06uAZYGNl96lgRO5XmyepEMK%2FC42uyJmcuZNLKk7xwxrvjc3Q9FVBCcwY0IA7A4MThDVoc7jw%3D%3D";

    public static final String ROUTE_LINE_TXT = "http://openapi.gbis.go.kr/ws/download?routeline"+today()+".txt";

    public static final String ROUTE_TXT = "http://openapi.gbis.go.kr/ws/download?route"+today()+".txt";

    OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor()).build();

    Retrofit retrofit = new Retrofit.Builder().baseUrl(SERVER_URL).client(client).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(SimpleXmlConverterFactory.create()).build();

    Service service = retrofit.create(Service.class);


    private static String today(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    private HttpLoggingInterceptor interceptor(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

}
