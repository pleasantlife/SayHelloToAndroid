package com.gandan.android.drawingprocess;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView4 = findViewById(R.id.imageView4);
        //코드단에서 이미지의 색상 변경하기
        imageView4.getDrawable().setColorFilter(Color.parseColor("#EAEAEA"), PorterDuff.Mode.SRC_ATOP);
    }
}
