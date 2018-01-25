package com.gandan.android.androidsmalltips;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     *  안드로이드에서 쓸 수 있는 아주아주아주 사소한 팁들을 공유해 보겠습니다.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         *  1. 원형배경 넣기
         */
        //1) TextView 선언
        TextView textViewCircleBorder = findViewById(R.id.textViewCircleBorder);
        //2) Layout Resource File(.xml)만들기 (여기서는 circle_background.xml로 생성)
        //3) TextView에 circle_background를 background로 등록 (activity_main.xml에서도 등록 할 수 있음)
        textViewCircleBorder.setBackgroundResource(R.drawable.circle_background);
        //4) 글자가 원의 중앙에 가도록 gravity 설정
        //5) 글자 색상 변경 (여기서는 흰색으로 변경)
        textViewCircleBorder.setTextColor(Color.WHITE);

        /**
         *  2. 라이브러리를 build.gradle에 등록할 때 문제가 생기면 exclude를 사용한다.(build.gradle 참고)
         */

    }
}
