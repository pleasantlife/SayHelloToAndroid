package com.gandan.android.lottiepractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animationView = findViewById(R.id.lottieTest);
        //XML에서 설정하면 아래 구문은 필요하지 않다.
        //animationView.setAnimation("loading_material.json");
        //animationView.playAnimation();

    }
}
