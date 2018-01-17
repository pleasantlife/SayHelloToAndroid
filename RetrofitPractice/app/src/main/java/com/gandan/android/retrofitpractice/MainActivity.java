package com.gandan.android.retrofitpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 *  레트로핏을 이용하여 받아온 데이터를 화면으로 나타낼 액티비티.
 *  (RecyclerView 사용)
 */

public class MainActivity extends AppCompatActivity {

    //레트로핏 연습에서 사용할 SERVER_URL을 미리 지정해준다.
    //해당 URL은 공개적인 URL이며, 여러 클래스에서 사용할 수도 있기 때문에 static으로 선언해두었다.
    public static String SERVER_URL = "http://httpbin.org/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
