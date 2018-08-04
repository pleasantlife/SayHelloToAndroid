package com.gandan.android.gandanbusjava;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gandan.android.gandanbusjava.adapter.LiveBusRecyclerAdapter;
import com.gandan.android.gandanbusjava.model.BusLocationList;
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
import static com.gandan.android.gandanbusjava.RetrofitInit.SERVICE_KEY;

public class BusRouteActivity extends AppCompatActivity {

    RetrofitInit retrofitInit = new RetrofitInit();
    String id;
    long routeId;
    List<BusRouteStation> routeStationList = new ArrayList<>();
    List<BusLocationList> liveList = new ArrayList<>();
    RecyclerView recyclerRouteDetail;
    LiveBusRecyclerAdapter liveBusRecyclerAdapter;
    TextView txtBusNumberRoute;
    Button btnRefresh;

    String decodedUrl;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_route);

        Log.e("Test", "here+");

        recyclerRouteDetail = findViewById(R.id.recyclerRouteDetail);
        txtBusNumberRoute = findViewById(R.id.txtBusNumberRoute);
        btnRefresh = findViewById(R.id.btnRefresh);
        liveBusRecyclerAdapter = new LiveBusRecyclerAdapter(this, routeStationList, liveList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        recyclerRouteDetail.setLayoutManager(linearLayoutManager);
        recyclerRouteDetail.setAdapter(liveBusRecyclerAdapter);
        recyclerRouteDetail.addItemDecoration(dividerItemDecoration);

        Intent intent = getIntent();
        id = intent.getStringExtra("busId");
        routeId = Long.parseLong(id);
        txtBusNumberRoute.setText(intent.getStringExtra("busNumber")+"번 버스");
        dialog = new AlertDialog.Builder(this).setMessage("실시간 버스 정보 검색중...").create();

        searchResult(routeId);

        btnRefresh.setOnClickListener(v -> {
            recyclerRouteDetail.setEnabled(false);
            routeStationList.clear();
            liveBusRecyclerAdapter.notifyDataSetChanged();
            searchResult(routeId);

        });

        {
            try {
                //서비스키에 있는 특수문자가 변형되지 않도록 디코딩을 꼭 해줘야 한다!!
                decodedUrl = URLDecoder.decode(SERVICE_KEY, "EUC_KR");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

    }

    private void searchResult(long routeId){
        dialog.show();
        routeStationList.clear();
        liveList.clear();
        Observable<ResponseBody> getRouteStationList = retrofitInit.service.getRouteStation(ROUTE_STATION_TXT);
        getRouteStationList.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try{
                    String routeStation = responseBody.string();
                    Log.e("start", routeId+"result");
                    for(String string : routeStation.split("\\^")) {
                        BusRouteStation routeStationInfo = new BusRouteStation();
                        if (string.split("\\|")[0].equals(routeId+"")) {
                            if (!string.split("\\|")[0].isEmpty()) {
                                routeStationInfo.setRouteId(string.split("\\|")[0]);
                            }
                            if (!string.split("\\|")[1].isEmpty()) {
                                routeStationInfo.setStationId(string.split("\\|")[1]);
                            }
                            if (!string.split("\\|")[2].isEmpty()) {
                                routeStationInfo.setUpDown(string.split("\\|")[2]);
                            }
                            if (!string.split("\\|")[3].isEmpty()) {
                                routeStationInfo.setStationOrder(string.split("\\|")[3]);
                            }
                            if (!string.split("\\|")[4].isEmpty()) {
                                routeStationInfo.setRouteNumber(string.split("\\|")[4]);
                            }
                            if (!string.split("\\|")[5].isEmpty()) {
                                routeStationInfo.setStationName(string.split("\\|")[5]);
                            }
                            routeStationList.add(routeStationInfo);
                            Log.e("routeStaSize", routeStationList.size() + "");
                            //liveBusRecyclerAdapter.notifyDataSetChanged();
                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("error RouteSta", e.getMessage()+"");

            }

            @Override
            public void onComplete() {
                Toast.makeText(BusRouteActivity.this, "검색 완료", Toast.LENGTH_SHORT).show();
                getLive();

            }
        });

    }

    private void getLive(){
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
                    liveList.add(locationList);
                }


            }

            @Override
            public void onError(Throwable e) {
                Log.e("error", e.getMessage()+"");
            }

            @Override
            public void onComplete() {
                liveBusRecyclerAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }
}
