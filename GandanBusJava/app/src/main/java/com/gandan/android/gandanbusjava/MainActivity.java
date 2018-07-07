package com.gandan.android.gandanbusjava;

import android.media.DrmInitData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gandan.android.gandanbusjava.adapter.LiveBusRecyclerAdapter;
import com.gandan.android.gandanbusjava.model.BusLocationList;
import com.gandan.android.gandanbusjava.model.Response;
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerLiveBus;
    LiveBusRecyclerAdapter liveBusRecyclerAdapter;
    List<BusLocationList> liveBusList = new ArrayList<>();


    public static final String SERVER_URL = "http://openapi.gbis.go.kr/ws/rest/";
    public static final String SERVICE_KEY = "0WsFS6QJRLa99LZJSCNP1RrAMA5qREUR32%2FTE8xm74m3UbOcabnwh4vYiukMJ9Sd%2FQ6oE69E%2B66sWm%2BTpXRVEg%3D%3D";


    private HttpLoggingInterceptor interceptor(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerLiveBus = findViewById(R.id.recyclerLiveBus);
        recyclerLiveBus.setLayoutManager(new LinearLayoutManager(this));
        liveBusRecyclerAdapter = new LiveBusRecyclerAdapter(this, liveBusList);
        recyclerLiveBus.setAdapter(liveBusRecyclerAdapter);

        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor()).build();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(SERVER_URL).client(client).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(SimpleXmlConverterFactory.create()).build();

        Service service = retrofit.create(Service.class);

        RequestBody serviceKeyBody = RequestBody.create(MediaType.parse("text/plain"), SERVICE_KEY);

        //routeID는 수원여객/용남고속 98번 버스.
        Observable<Response> getLive = service.getLiveBus(SERVICE_KEY, 200000085);
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
                    liveBusRecyclerAdapter.notifyDataSetChanged();
                }

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
