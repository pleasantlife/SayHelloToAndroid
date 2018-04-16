package com.gandan.android.naveraddresspractice;

import com.gandan.android.naveraddresspractice.AddressModel.StoreAddress;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by XPS on 2018-04-16.
 */

public interface AddressService {

    @GET("v1/map/geocode")
    Observable<StoreAddress> getAddress(@Header("X-Naver-Client-Id") String clientId, @Header("X-Naver-Client-Secret") String clientSecret, @Query("query") String query);
}
