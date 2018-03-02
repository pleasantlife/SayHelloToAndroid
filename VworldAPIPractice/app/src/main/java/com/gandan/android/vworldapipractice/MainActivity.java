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

/**
 *  국토교통부의 지리정보 관련 사이트인 'Vworld'에서 제공하는 주소검색 API를 이용하여, 원하는 주소를 검색하고
 * 검색 결과를 RecyclerView에 출력해본다.
 */

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

        /**
         *  RxJava와 RxBinding 라이브러리를 이용해 InputText에 입력되는 글자를 실시간으로 확인할 수 있다.
         */
        //Observable의 Type을 String으로 하면, 내용을 제대로 받아오지 못한다.
        Observable<CharSequence> addressInput = RxTextView.textChanges(inputRoadAdress);
        //안드로이드의 메인스레드에서 observe를 해야한다.
        addressInput.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CharSequence>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CharSequence charSequence) {
                //글자를 한 자라도 입력하면 바로 주소를 검색하도록 했다.
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

    /**
     *  Retrofit을 통해 검색한 주소를 받아오는 메소드이다.
     */
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
