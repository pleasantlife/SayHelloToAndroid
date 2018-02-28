package com.gandan.android.vworldapipractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gandan.android.vworldapipractice.Model.Address;
import com.gandan.android.vworldapipractice.Model.AddressQuery;
import com.gandan.android.vworldapipractice.Model.Item;
import com.jakewharton.rxbinding2.widget.RxTextView;

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
    List<String> roadList = new ArrayList<>();
    EditText inputRoadAdress;
    RecycleAdapter recycleAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputRoadAdress = findViewById(R.id.inputRoadAdress);
        recyclerView = findViewById(R.id.recyclerView);
        recycleAdapter = new RecycleAdapter(this, roadList);
        recyclerView.setAdapter(recycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Observable<CharSequence> addressInput = RxTextView.textChanges(inputRoadAdress);
        addressInput.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CharSequence>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CharSequence charSequence) {
                if(charSequence.length() >= 1) {
                    String query = charSequence.toString();
                    getAddressList(query);
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

    private void getAddressList(String query){
        Observable<AddressQuery> getAddress = retrofitInit.vworldService.getAddress(AUTH_KEY, "search", "address", "road", "json", 1000, query);

        getAddress.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<AddressQuery>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AddressQuery addressQuery) {
                roadList.clear();
                for(Item item : addressQuery.getResponse().getResult().getItems()){
                    roadList.add(item.getAddress().getRoad());
                    recycleAdapter.notifyDataSetChanged();
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
