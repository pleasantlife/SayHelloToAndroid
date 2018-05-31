package com.gandan.android.buttonflickering;

import android.content.Intent;
import android.graphics.Color;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 *  안드로이드의 버튼색이 점차적으로 바뀌도록 하는 예제 (자바 사용)
 */

public class MainActivity extends AppCompatActivity {

    Button btnFlicker;
    String color, fontColor;
    int count = 0;
    Thread thread;


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
                thread.interrupt();
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
        //thread = new Thread(this::setButtonColor);

        //람다를 사용하지 않았을 경우
        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                setButtonColor();
            }
        }); */
        //thread.start();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        setButtonColor();
    }

    //자기자신을 호출하여 끊임없이 코드를 수행하는 재귀함수가 됨.
    private String setButtonColor() {

        if(count == 0) {
            Handler emitter = new Handler();
            emitter.postDelayed(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i <= 99; i++) {
                        colorChange(i);
                    }
                }
            },1000);

            //카운트를 먼저 바꿔준 후,
            count = 1;
            //자기 자신을 호출(재귀)
            Handler handler = new Handler();
            handler.post(this::setButtonColor);
            //setButtonColor();
            return color;
        }
        if(count == 1){
           Handler minusEmitter = new Handler();
           minusEmitter.postDelayed(new Runnable() {
               @Override
               public void run() {
                   for (int i = 99; i >= 0; i--) {
               /* try {
                    //Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                       colorChange(i);
                   }
               }
           }, 1000);
            //카운트를 먼저 바꿔준 후,
            count = 0;
            //자기 자신을 호출(재귀)
            //setButtonColor();
            Handler handler = new Handler();
            handler.post(this::setButtonColor);
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
            /*runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    btnFlicker.setTextColor(Color.parseColor(fontColor));
                }
            });*/
            //btnFlicker.setTextColor(Color.parseColor(fontColor));
        }
        if(minus < 10) {
            fontColor = "#0" + minus + "000000";
            /*runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    btnFlicker.setTextColor(Color.parseColor(fontColor));
                }
            });*/

        }
        btnFlicker.setBackgroundColor(Color.parseColor(color));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },100);
        Log.e("colorSet", btnFlicker.getBackground() + "");
       /* try {
            //sleep 값의 변화로 색상이 변하는 시간을 조정할 수 있다.

        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
