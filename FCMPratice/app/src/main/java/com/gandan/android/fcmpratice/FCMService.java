package com.gandan.android.fcmpratice;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by XPS on 2018-03-29.
 */

// FCM의 메세지 내용을 서버로 보내서 앱이 설치된 기기에 노티를 보낼 수 있도록 하는 레트로핏 인터페이스.

public interface FCMService {

    @POST("fcm/send")
    Call<Void> sendMessage(@Header("Content-Type") String contentType, @Header("Authorization") String key, @Body FCMSend fcmSend);
}
