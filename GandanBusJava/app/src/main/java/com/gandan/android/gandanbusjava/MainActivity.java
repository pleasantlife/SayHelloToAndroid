package com.gandan.android.gandanbusjava;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gandan.android.gandanbusjava.adapter.BusNumberSearchAdapter;
import com.gandan.android.gandanbusjava.adapter.LiveBusRecyclerAdapter;
import com.gandan.android.gandanbusjava.model.BusLocationList;
import com.gandan.android.gandanbusjava.model.BusRoute;
import com.gandan.android.gandanbusjava.model.BusRouteStation;
import com.gandan.android.gandanbusjava.model.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import static com.gandan.android.gandanbusjava.RetrofitInit.ROUTE_STATION_TXT;
import static com.gandan.android.gandanbusjava.RetrofitInit.ROUTE_TXT;
import static com.gandan.android.gandanbusjava.RetrofitInit.SERVICE_KEY;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RouteIdListener {


    RecyclerView recyclerLiveBus;
    LiveBusRecyclerAdapter liveBusRecyclerAdapter;
    BusNumberSearchAdapter busNumberSearchAdapter;

    List<BusLocationList> liveBusList = new ArrayList<>();
    List<BusRouteStation> routeStationList = new ArrayList<>();

    TextInputEditText inputBusRouteNumber;
    ImageView btnDoRouteSearch;

    RetrofitInit retrofitInit;



    List<BusRoute> routeList = new ArrayList<>();
    List<BusRoute> routeSearchList = new ArrayList<>();



    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofitInit = new RetrofitInit();

        inputBusRouteNumber = findViewById(R.id.inputBusRouteNumber);
        //inputBusRouteNumber.setHint("BusNumber");

        inputBusRouteNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                switch (actionId){
                    case EditorInfo.IME_ACTION_SEARCH:
                        routeSearch();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        recyclerLiveBus = findViewById(R.id.recyclerLiveBus);
        recyclerLiveBus.setLayoutManager(new LinearLayoutManager(this));
        busNumberSearchAdapter = new BusNumberSearchAdapter(this, routeSearchList);
        recyclerLiveBus.setAdapter(busNumberSearchAdapter);
        //recyclerLiveBus.setAdapter(liveBusRecyclerAdapter);

        btnDoRouteSearch = findViewById(R.id.btnDoRouteSearch);

        /*Glide.with(this).load("https://cdn.pixabay.com/photo/2018/07/08/14/16/cat-3523992_1280.jpg")
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE).skipMemoryCache(true)).into(btnDoRouteSearch);*/

        /*Observable<ResponseBody> getRouteLineList = retrofitInit.service.getRouteLines(ROUTE_LINE_TXT);
        getRouteLineList.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String routeLine = responseBody.string();
                    BusRouteLine line = new BusRouteLine();

                    for(String string : routeLine.split("\\^")){
                        Log.e("routeLine", string+"");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Error", e.getMessage()+"");
            }

            @Override
            public void onComplete() {

            }
        });*/

        //
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
                Toast.makeText(MainActivity.this, "검색 가능", Toast.LENGTH_SHORT).show();
            }
        });

        btnDoRouteSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.btnDoRouteSearch:
                routeSearch();

                break;

        }
    }

    private void routeSearch(){

        routeSearchList.clear();
        Log.e("routeSize", routeList.size() + "");
        for (BusRoute route : routeList) {
            if (route.getRouteNumber().contains(inputBusRouteNumber.getText().toString())) {
                routeSearchList.add(route);
            }
        }
        busNumberSearchAdapter.notifyDataSetChanged();
    }

    @Override
    public void getRouteId(long routeId) {
        //recyclerLiveBus.setAdapter(liveBusRecyclerAdapter);
        //searchResult(routeId);
    }
}
