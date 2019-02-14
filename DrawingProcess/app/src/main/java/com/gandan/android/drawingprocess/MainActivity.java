package com.gandan.android.drawingprocess;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    ImageView imageView4;
    Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView4 = findViewById(R.id.imageView4);
        //코드단에서 이미지의 색상 변경하기
        imageView4.getDrawable().setColorFilter(Color.parseColor("#EAEAEA"), PorterDuff.Mode.SRC_ATOP);

        switch1 = findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    switch1.getTrackDrawable().setColorFilter(Color.parseColor("#1ABC9C"), PorterDuff.Mode.SRC_IN);
                } else {
                    switch1.getTrackDrawable().setColorFilter(Color.parseColor("#EAEAEA"), PorterDuff.Mode.SRC_IN);
                }
            }
        });
    }
}
