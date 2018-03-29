package com.gandan.android.fcmpratice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    FirebaseInstanceId firebaseInstanceId;
    Button btnSendNoti;
    FCMService fcmService;
    Retrofit retrofit;

    private static String SENDER_ID = "980722477268";
    private static String FCM_URL = "https://fcm.googleapis.com/";
    private static String OLD_SERVER_KEY = "AIzaSyAEPoemVXtGVZNkxVl5kgA7-EWV1ocgTGk";
    private static String SERVER_KEY = "AAAA5FedaNQ:APA91bEHC0fr7D21EncvmRIIGNGq9kgTpSsLIBy5AdBpMqro4TYk5UI5mLLZdK0zcoh9OYpCz7duOMZzuH3wZi4asNiAFdj9pNkMrwC3Qf5E0JN593Eb3pRTi2rkc3g4tou-u1qJZkrP";
    private static String TEST_TOKEN_LG = "dwWAhBc0b2k:APA91bG4GvRFxZsxyRn6NvciGp-E7lWyO_XqMcivsWImD39AOT3thgCVjgECVyzKletbdJEk2xd9jDLq3VKzqgjX3302FcFqDhw29Mc4QU-wpLS5vipZY51xwF1l2zkMV4venYXUKI0l";
    private static String TEST_TOKEN = "dWLmIDCa6Q0:APA91bHfFUZ6uEivAp4qoewzrZNNGqzIZRNDH1d9Tne8o0csN0K9C6hxT1tgey4KEuqS6-D8RQ1ExOZTYoqTBoIS8ijlFa6t0FRwTLrmZgQBey011gMoXMMaSEnGhx4Lw6hxelqNFu4t";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseInstanceId = FirebaseInstanceId.getInstance();
        String token = firebaseInstanceId.getToken();
        Log.e("token" ,token+"");
        btnSendNoti = findViewById(R.id.btnSendNoti);
        btnSendNoti.setOnClickListener( v -> sendNoti());
        activeRetrofit();
    }

    private void activeRetrofit(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("okhttp : ", message+"");
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
        retrofit = new Retrofit.Builder().baseUrl(FCM_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
        fcmService = retrofit.create(FCMService.class);

    }

    //firebase 콘솔을 통하지 않고, 기기에서 기기로 노티를 보낼 수 있다!
    private void sendNoti(){
        Data data = new Data();
        data.setTitle("Hello from App");
        data.setMessage("Send Message from Application!");
        FCMSend fcmSend = new FCMSend();
        fcmSend.setData(data);
        fcmSend.setTo(TEST_TOKEN);
        //API 26버전 이상에 적용되는 '알림채널'에 대응하기 위해 채널 id를 기입했다.
        //알림채널을 명시하지 않으면, 앱이 노티를 띄우지 않는다.
        fcmSend.setAndroidChannelId(getString(R.string.fcm));

        Call<Void> sendMessage = fcmService.sendMessage("application/json", "key="+OLD_SERVER_KEY, fcmSend);
        sendMessage.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "성공!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                if(t.getMessage() != null){
                    Log.e("error", t.getMessage()+"");
                }
            }
        });
    }


}
