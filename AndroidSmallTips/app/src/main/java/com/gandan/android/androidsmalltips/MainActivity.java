package com.gandan.android.androidsmalltips;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
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

        /**
         *  3. 그라데이션 배경 넣기
         */
        //1) drawable 폴더에 gradident 속성을 정한 xml 파일을 저장한다.
        //2) 그라데이션 배경을 넣을 뷰를 선언한다.
        ConstraintLayout backLayout = findViewById(R.id.backLayout);
        //3) 2에서 선언한 뷰에 setBackgroundResource를 통해 설정해준다.
        backLayout.setBackgroundResource(R.drawable.gradient);
        //3-1) 만약 layout 폴더에 등록된 레이아웃 xml의 배경에 넣는다면 직접 xml을 수정하여, 배경등록도 가능하다.
        //참고 : 아래와 같은 방식으로 #으로 표현되는 컬러코드를 등록하는 것도 가능하다.
        Color.parseColor("#66666");

        /**
         *  4. 한글 줄바꿈 자연스럽게 하기
         */
        //TextView로 긴 글을 쓰면, 특정한 줄의 오른쪽 끝에 공간이 많이 남는 경우가 있다.
        //그럴 때에는 TextView의 속성에서 breakStrategy를 simple로 적용하면, 남는 공간을 크게 줄일 수 있다.
        TextView textViewBefore = findViewById(R.id.textViewBefore);
        TextView textViewAfter = findViewById(R.id.textVIewAfter);
        textViewBefore.setText(getString(R.string.long_text));
        textViewAfter.setText(getString(R.string.long_text));
    }
}
