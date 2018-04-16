package com.gandan.android.naveraddresspractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gandan.android.naveraddresspractice.AddressModel.Item;
import com.gandan.android.naveraddresspractice.AddressModel.StoreAddress;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String NAVER_CLIENT_ID = "A2xhsGQbN3PTla1zU1bk";
    public static final String NAVER_CLIENT_SECRET = "0CSjJD2lb7";
    public static final String NAVER_URL = "https://openapi.naver.com/";

    RetrofitInit retrofitInit = new RetrofitInit();

    RecyclerView recyclerAddress;
    Button btnDoSearchAddress;
    EditText inputAddress;

    List<Item> resultList = new ArrayList<>();
    AddressAdapter addressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerAddress = findViewById(R.id.recyclerAddress);
        btnDoSearchAddress = findViewById(R.id.btnDoSearchAddress);
        btnDoSearchAddress.setOnClickListener(this);
        inputAddress = findViewById(R.id.inputAddress);
        addressAdapter = new AddressAdapter(this, resultList);
        recyclerAddress.setAdapter(addressAdapter);
        recyclerAddress.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnDoSearchAddress:
                getAddress();
                break;
        }
    }

    private void getAddress(){
        String query = inputAddress.getText().toString();
        Observable<StoreAddress> getData = retrofitInit.addressService.getAddress(NAVER_CLIENT_ID, NAVER_CLIENT_SECRET, query);
        getData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<StoreAddress>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(StoreAddress storeAddress) {
                resultList.clear();
                for(Item item : storeAddress.getResult().getItems()){
                    resultList.add(item);
                }
                addressAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "검색결과가 없습니다.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
