package com.gandan.android.kakaoaddress;

import com.gandan.android.kakaoaddress.AddressModel.StoreAddress;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by XPS on 2018-04-14.
 */

public interface KakaoAddressService {

    //'address.json'으로 return type을 지정해 주어야 한다.
    @GET("v2/local/search/address.json")
    Observable<StoreAddress> getData(@Header("Authorization") String token, @Query("query") String query);

}
