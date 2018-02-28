package com.gandan.android.vworldapipractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.gandan.android.vworldapipractice.Model.Address;
import com.gandan.android.vworldapipractice.Model.AddressQuery;
import com.gandan.android.vworldapipractice.Model.Item;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.gandan.android.vworldapipractice.RetrofitInit.AUTH_KEY;

public class MainActivity extends AppCompatActivity {

    RetrofitInit retrofitInit = new RetrofitInit();
    List<AddressQuery> addressQueryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable<AddressQuery> getAddress = retrofitInit.vworldService.getAddress(AUTH_KEY, "search", "address", "road", "json", 1000, "향남읍");

        getAddress.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<AddressQuery>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AddressQuery addressQuery) {
                for(Item item : addressQuery.getResponse().getResult().getItems()){
                    Log.e("road", item.getAddress().getRoad()+"");
                }
                ;
                /*for(AddressQuery addressQuery : addressQueries){
                    Log.e("addressQuery", addressQuery.toString()+"");
                }*/
               /* for(AddressQuery addressQuery : addressQueries){
                    for(Item item : addressQuery.getResponse().getResult().getItems()){
                        Log.e("address", item.getAddress()+"");
                    }

                }*/
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
