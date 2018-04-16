package com.gandan.android.naveraddresspractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String NAVER_CLIENT_ID = "A2xhsGQbN3PTla1zU1bk";
    public static final String NAVER_CLIENT_SECRET = "0CSjJD2lb7";
    private static final String NAVER_URL = "https://openapi.naver.com/";

    RecyclerView recyclerAddress;
    Button btnDoSearchAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerAddress = findViewById(R.id.recyclerAddress);
        btnDoSearchAddress = findViewById(R.id.btnDoSearchAddress);
        btnDoSearchAddress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnDoSearchAddress:

                break;
        }
    }
}
