package com.gandan.android.butterknifepractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //1. ButterKnife 사용법
    //1) ButterKnife를 사용하기 위해 Annotation으로 BindView를 선언해준다.
    //   (선언하면 Butterknife에서 자동으로 레이아웃에서 해당 id를 찾는다.)
    //2) 괄호 안에 뷰의 id를 넣어준다.
    //3) id에 바인딩할 데이터를 가진 타입과 변수를 선언한다.
    @BindView(R.id.textView1)
    TextView textView1;

    @BindView(R.id.textView2)
    TextView textView2;

    @BindView(R.id.btnButton1)
    Button btnButton1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //4) 버터나이프로 바인딩함을 선언해준다.
        //findViewById를 선언할 수 있는 곳이면, 어디든 .bind()를 사용할 수 있다.
        ButterKnife.bind(this);
        textView1.setText("Test Text");

        /**ButterKnife를 사용하지 않으면?
        * TextView noButtertextView1 = findViewById(R.id.textView1);
        * TextView noButtertextView2 = findViewById(R.id.textView2);
        * Button btnButton1 = findViewById(R.id.btnButton1);
        * btnButton1.setOnClickListener(this);
         */
    }

    //5) Button처럼 OnClickListener를 달아야 한다면, 아래와 같이 함수로 처리하면 된다.
    //이 함수는 Annotation이 있어서, 다른 함수에 따로 선언하지 않아도 된다.
    //단, private이나 static 함수로는 선언할 수 없다.
    @OnClick(R.id.btnButton1)
    public void btnChange(Button button){
        button.setText("Hello!");
        textView2.setText("Hello World!");
    }

    @Override
    public void onClick(View v) {
        /**
         *  Butter Knife를 사용하지 않았을 때!

         switch(v.getId()){
            case R.id.btnButton1:
                btnButton1.setText("Hello");
                textView2.setText("Hello World!");
            break;
        }
         */
    }
}
