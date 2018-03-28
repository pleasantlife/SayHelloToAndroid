package com.gandan.android.fcmpratice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

public class MainActivity extends AppCompatActivity {

    FirebaseInstanceId firebaseInstanceId;
    Button btnSendNoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseInstanceId = FirebaseInstanceId.getInstance();
        String token = firebaseInstanceId.getToken();
        Log.e("token" ,token+"");
        btnSendNoti = findViewById(R.id.btnSendNoti);
        btnSendNoti.setOnClickListener( v -> sendNoti());
    }

    private void sendNoti(){
    }

}
