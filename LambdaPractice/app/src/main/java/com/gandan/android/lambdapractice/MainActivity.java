package com.gandan.android.lambdapractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 *  람다 표현 사용해보기
 */
public class MainActivity extends AppCompatActivity {

    StreamTest streamTest;

    Button btnLambda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLambda = findViewById(R.id.btnLambda);
        //람다식 미사용
        btnLambda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "람다 쓰지 않았어요!", Toast.LENGTH_SHORT).show();
            }
        });
        //람다식 사용
        btnLambda.setOnClickListener( v -> Toast.makeText(this, "람다 썼어요!", Toast.LENGTH_SHORT).show());

        makeThread();

        //인터페이스를 람다식으로 대체!
        Function function = (int a, int b) -> a + b;
        int result = function.calc(3,4);
        Log.e("function", result+"");

        streamTest = new StreamTest();
        streamTest.setMyList();
    }


    private void count(){
        for(int i =1; i <= 100; i++) {
            Log.d("Count", i+"");
        }
    }

    private void makeThread(){
        //람다식 미사용
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                count();
            }
        });
        //람다식 사용
        Thread lambdaThread = new Thread(() -> count() );

        //람다식 심화사용
        Thread lambdaAdvanceThread = new Thread(this::count);
    }



}
