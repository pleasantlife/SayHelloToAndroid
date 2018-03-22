package com.gandan.android.fcmpratice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    FirebaseInstanceId firebaseInstanceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseInstanceId = FirebaseInstanceId.getInstance();
        String token = firebaseInstanceId.getToken();
        Log.e("token" ,token+"");
    }


}
