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
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
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

import static com.gandan.android.gandanbusjava.RetrofitInit.ROUTE_TXT;
import static com.gandan.android.gandanbusjava.RetrofitInit.SERVICE_KEY;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RouteIdListener {


    RecyclerView recyclerLiveBus;
    LiveBusRecyclerAdapter liveBusRecyclerAdapter;
    BusNumberSearchAdapter busNumberSearchAdapter;

    List<BusLocationList> liveBusList = new ArrayList<>();

    EditText inputBusRouteNumber;
    ImageView btnDoRouteSearch;

    RetrofitInit retrofitInit;




    String decodedUrl;

    StringBuffer sb = new StringBuffer();


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

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofitInit = new RetrofitInit();

        inputBusRouteNumber = findViewById(R.id.inputBusRouteNumber);

        recyclerLiveBus = findViewById(R.id.recyclerLiveBus);
        recyclerLiveBus.setLayoutManager(new LinearLayoutManager(this));
        liveBusRecyclerAdapter = new LiveBusRecyclerAdapter(this, liveBusList);
        busNumberSearchAdapter = new BusNumberSearchAdapter(this, routeSearchList);
        recyclerLiveBus.setAdapter(busNumberSearchAdapter);
        //recyclerLiveBus.setAdapter(liveBusRecyclerAdapter);

        btnDoRouteSearch = findViewById(R.id.btnDoRouteSearch);

        Glide.with(this).load("https://cdn.pixabay.com/photo/2018/07/08/14/16/cat-3523992_1280.jpg")
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE).skipMemoryCache(true)).into(btnDoRouteSearch);



        Observable<ResponseBody> getRouteList = retrofitInit.service.getRoutes(ROUTE_TXT);
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
                        if(!string.split(("\\|"))[0].isEmpty()) {
                            route.setRouteId(string.split("\\|")[0]);
                        }
                        if(!string.split("\\|")[1].isEmpty()) {
                            route.setRouteNumber(string.split("\\|")[1]);
                        }
                        if(!string.split("\\|")[2].isEmpty()){
                            route.setRouteTp(string.split("\\|")[2]);
                        }
                        if(!string.split("\\|")[3].isEmpty()){
                            route.setStartStationId(string.split("\\|")[3]);
                        }
                        if(!string.split("\\|")[4].isEmpty()){
                            route.setRouteEndStationName(string.split("\\|")[4]);
                        }
                        if(!string.split("\\|")[5].isEmpty()){
                            route.setRouteEndStationNumber(string.split("\\|")[5]);
                        }
                        if(!string.split("\\|")[6].isEmpty()){
                            route.setEndStationId(string.split("\\|")[6]);
                        }
                        if(!string.split("\\|")[7].isEmpty()){
                            route.setRouteEndStationName(string.split("\\|")[7]);
                        }
                        if(!string.split("\\|")[8].isEmpty()){
                            route.setRouteEndStationNumber(string.split("\\|")[8]);
                        }
                        if(!string.split("\\|")[9].isEmpty()){
                            route.setUpFirstTime(string.split("\\|")[9]);
                        }
                        if(!string.split("\\|")[10].isEmpty()){
                            route.setUpLastTime(string.split("\\|")[10]);
                        }
                        if(!string.split("\\|")[11].isEmpty()){
                            route.setDownFirstTime(string.split("\\|")[11]);
                        }
                        if(!string.split("\\|")[12].isEmpty()){
                            route.setDownLastTime(string.split("\\|")[12]);
                        }
                        if(!string.split("\\|")[13].isEmpty()){
                            route.setPeakAllocation(string.split("\\|")[13]);
                        }
                        if(!string.split("\\|")[14].isEmpty()){
                            route.setnPeakAllocation(string.split("\\|")[14]);
                        }
                        if(!string.split("\\|")[15].isEmpty()){
                            route.setCompanyId(string.split("\\|")[15]);
                        }
                        if(!string.split("\\|")[16].isEmpty()){
                            route.setCompanyName(string.split("\\|")[16]);
                        }
                        if(!string.split("\\|")[17].isEmpty()){
                            route.setTellNumber(string.split("\\|")[17]);
                        }
                        if(!string.split("\\|")[18].isEmpty()){
                            route.setRegionName(string.split("\\|")[18]);
                        }
                        if(!string.split("\\|")[19].isEmpty()){
                            route.setDistrictCd(string.split("\\|")[19]);
                        }

                        routeList.add(route);
                    }


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

        btnDoRouteSearch.setOnClickListener(this);



    }

    private void searchResult(long routeId){
        Observable<Response> getLive = retrofitInit.service.getLiveBus(decodedUrl, routeId);
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

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.btnDoRouteSearch:
                routeSearchList.clear();
                Log.e("routeSize", routeList.size()+"");
                for(BusRoute route : routeList){
                    if(route.getRouteNumber().contains(inputBusRouteNumber.getText().toString())){
                        routeSearchList.add(route);
                    }
                }
                busNumberSearchAdapter.notifyDataSetChanged();
                break;

        }
    }

    @Override
    public void getRouteId(long routeId) {
        recyclerLiveBus.setAdapter(liveBusRecyclerAdapter);
        searchResult(routeId);
    }
}
