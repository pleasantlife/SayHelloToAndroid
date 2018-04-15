package com.gandan.android.kakaoaddress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gandan.android.kakaoaddress.AddressModel.Document;
import com.gandan.android.kakaoaddress.AddressModel.StoreAddress;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gandan.android.kakaoaddress.RetrofitInit.KAKAO_KEY;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    RetrofitInit retrofitInit = new RetrofitInit();
    EditText inputSearchAddress;
    Button btnDoSearchAddress;
    List<Document> documentList = new ArrayList<>();
    AddressAdapter addressAdapter;
    RecyclerView recyclerKakaoAddress;
    TextView txtNoResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputSearchAddress = findViewById(R.id.inputSearchAddress);
        btnDoSearchAddress = findViewById(R.id.btnDoSearchAddress);
        btnDoSearchAddress.setOnClickListener(this);
        addressAdapter = new AddressAdapter(this, documentList);
        recyclerKakaoAddress = findViewById(R.id.recyclerKakaoAddress);
        recyclerKakaoAddress.setAdapter(addressAdapter);
        recyclerKakaoAddress.setLayoutManager(new LinearLayoutManager(this));
        txtNoResult = findViewById(R.id.txtNoResult);
        txtNoResult.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnDoSearchAddress:
                loadData();
                break;
        }
    }


    private void loadData(){
        Observable<StoreAddress> getAddressDatas = retrofitInit.kakaoAddressService.getData("KakaoAK "+KAKAO_KEY, inputSearchAddress.getText().toString());
        getAddressDatas.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<StoreAddress>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(StoreAddress storeAddress) {
                documentList.clear();
                for(Document document : storeAddress.getDocuments()){
                    documentList.add(document);
                }
                //notify가 onComplete에 있으면 제대로 적용되지 않는다.
                addressAdapter.notifyDataSetChanged();
                if(documentList.size() == 0){
                    txtNoResult.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
