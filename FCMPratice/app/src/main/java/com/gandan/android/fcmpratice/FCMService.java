package com.gandan.android.fcmpratice;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by XPS on 2018-03-29.
 */

public interface FCMService {

    @POST("fcm/send")
    Call<Void> sendMessage(@Header("Content-Type") String contentType, @Header("Authorization") String key, @Body FCMSend fcmSend);
}
