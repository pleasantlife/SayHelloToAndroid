package com.gandan.android.gandanbusjava;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gandan.android.gandanbusjava.adapter.BusNumberSearchAdapter;
import com.gandan.android.gandanbusjava.adapter.LiveBusRecyclerAdapter;
import com.gandan.android.gandanbusjava.model.BusLocationList;
import com.gandan.android.gandanbusjava.model.BusRoute;
import com.gandan.android.gandanbusjava.model.Response;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerLiveBus;
    LiveBusRecyclerAdapter liveBusRecyclerAdapter;
    BusNumberSearchAdapter busNumberSearchAdapter;

    List<BusLocationList> liveBusList = new ArrayList<>();

    EditText inputBusRouteNumber;
    Button btnDoRouteSearch;


    public static final String SERVER_URL = "http://openapi.gbis.go.kr/ws/rest/";

    public static final String SERVICE_KEY = "iha9k%2Fpy8ZJHW06uAZYGNl96lgRO5XmyepEMK%2FC42uyJmcuZNLKk7xwxrvjc3Q9FVBCcwY0IA7A4MThDVoc7jw%3D%3D";

    public final String ROUTE_TXT = "http://openapi.gbis.go.kr/ws/download?route"+today()+".txt";

    String decodedUrl;

    StringBuffer sb = new StringBuffer();

    Service service;

    List<BusRoute> routeList = new ArrayList<>();
    List<BusRoute> routeSearchList = new ArrayList<>();

    {
        try {
            //서비스키에 있는 특수문자가 변형되지 않도록 디코딩을 꼭 해줘야 한다!!
            decodedUrl = URLDecoder.decode(SERVICE_KEY, "EUC_KR");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private String today(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        return simpleDateFormat.format(System.currentTimeMillis());
    }



    private HttpLoggingInterceptor interceptor(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputBusRouteNumber = findViewById(R.id.inputBusRouteNumber);
        btnDoRouteSearch = findViewById(R.id.btnDoRouteSearch);
        recyclerLiveBus = findViewById(R.id.recyclerLiveBus);
        recyclerLiveBus.setLayoutManager(new LinearLayoutManager(this));
        liveBusRecyclerAdapter = new LiveBusRecyclerAdapter(this, liveBusList);
        //recyclerLiveBus.setAdapter(liveBusRecyclerAdapter);

        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor()).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(SERVER_URL).client(client).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(SimpleXmlConverterFactory.create()).build();

        service = retrofit.create(Service.class);


        Observable<ResponseBody> getRouteList = service.getRoutes(ROUTE_TXT);
        getRouteList.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String routes = responseBody.string();

                    for(String string : routes.split("\\^")){
                        BusRoute route = new BusRoute();
                        if(string.split(("\\|"))[0].isEmpty()) {
                            route.setRouteId(Long.parseLong(string.split("\\|")[0]));
                        }
                        if(string.split("\\|")[1].isEmpty()) {
                            route.setRouteNumber(string.split("\\|")[1]);
                        }
                        if(string.split("\\|")[2].isEmpty()){
                            route.setRouteTp(Integer.parseInt(string.split("\\|")[2]));
                        }
                        //Log.e("split", string.split("\\|")[0]);
                        //Log.e("splitTwo", string.split("\\|")[1]);
                        //Log.e("splitThree", string.split("\\|")[2]);
                        //Log.e("splitFour", string.split("\\|")[3]);
                        //Log.e("splitFive", string.split("\\|")[4]);
                        //Log.e("splitSix", string.split("\\|")[5]);
                        routeList.add(route);
                    }
                    busNumberSearchAdapter = new BusNumberSearchAdapter(MainActivity.this, routeSearchList);
                    recyclerLiveBus.setAdapter(busNumberSearchAdapter);
                    Log.e("route", routeList.size()+"");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("error", "download file");
            }

            @Override
            public void onComplete() {
                Log.e("stringBuffer", sb.toString()+"");
                Toast.makeText(MainActivity.this, "검색 가능", Toast.LENGTH_SHORT).show();
            }
        });


        btnDoRouteSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(BusRoute route : routeList){
                    if(route.getRouteNumber().contains(inputBusRouteNumber.getText())){
                        routeSearchList.add(route);
                    }

                }
                if(routeList.size() > 0){
                    busNumberSearchAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "검색결과 없음", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void searchResult(){
        Observable<Response> getLive = service.getLiveBus(decodedUrl, 200000085);
        getLive.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response response) {
                Log.e("response", response.getMsgHeader().getQueryTime());
                Log.e("error", response.getComMsgHeader().getErrMsg()+"");
                Log.e("success?", response.getMsgHeader().getResultMessage());
                Log.e("busList", response.getMsgBody().getBusLocationList().get(0).getPlateNo());
                for(BusLocationList locationList : response.getMsgBody().getBusLocationList()){
                    liveBusList.add(locationList);
                    recyclerLiveBus.swapAdapter(liveBusRecyclerAdapter, true);

                }
                liveBusRecyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onError(Throwable e) {
                Log.e("error", e.getMessage()+"");
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
