package com.gandan.android.buttonflickering;

import android.content.Intent;
import android.graphics.Color;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 *  안드로이드의 버튼색이 점차적으로 바뀌도록 하는 예제
 */

public class MainActivity extends AppCompatActivity {

    Button btnFlicker;
    String color, fontColor;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFlicker = findViewById(R.id.btnFlicker);
        //Java 8 Lambda 적용
        btnFlicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), FlickerKotlin.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        /*//RxJava를 사용하면 Thread를 사용하지 않아도, 실시간으로 String color 값의 변화를 감지한다.
        Observable<String> observable = Observable.create( e -> e.onNext(setButtonColor()));

        //람다를 사용하지 않았을 경우
        *//*Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(setButtonColor());
            }
        });*//*
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();*/

        //람다식 사용
        Thread thread = new Thread(this::setButtonColor);

        //람다를 사용하지 않았을 경우
        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                setButtonColor();
            }
        }); */
        thread.start();
    }

    //자기자신을 호출하여 끊임없이 코드를 수행하는 재귀함수가 됨.
    private String setButtonColor() {

        if(count == 0) {
            for (int i = 0; i <= 99; i++) {
                colorChange(i);
            }
            //카운트를 먼저 바꿔준 후,
            count = 1;
            //자기 자신을 호출(재귀)
            setButtonColor();
            return color;
        }
        if(count == 1){
            for (int i = 99; i >= 0; i--) {
                colorChange(i);
            }
            //카운트를 먼저 바꿔준 후,
            count = 0;
            //자기 자신을 호출(재귀)
            setButtonColor();
            return color;
        }
        return color;
    }

    //버튼 배경색과 글자색을 변하게 하는 메소드
    private void colorChange(int i){
        if(i < 10){
            color = "#0" + i + "50C1F3";
        } else if(i >= 10){
            color = "#" + i + "50c1f3";
        }
        btnFlicker.setBackgroundColor(Color.parseColor(color));
        int minus = 99-i;
        if(minus >= 10) {
            fontColor = "#"+ minus + "000000";
            btnFlicker.setTextColor(Color.parseColor(fontColor));
        }
        if(minus < 10) {
            fontColor = "#0" + minus + "000000";
            btnFlicker.setTextColor(Color.parseColor(fontColor));
        }
        Log.e("colorSet", btnFlicker.getBackground() + "");
        try {
            //sleep 값의 변화로 색상이 변하는 시간을 조정할 수 있다.
            Thread.sleep(10);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
