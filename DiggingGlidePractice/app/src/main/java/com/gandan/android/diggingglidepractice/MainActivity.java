package com.gandan.android.diggingglidepractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity extends AppCompatActivity {

    //Glide 사용을 위해 AndroidManifest에서 Internet Permission을 줘야함.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgGlideBasic = findViewById(R.id.imgGlideBasic);
        //Glide 기본적인 사용법.(웹 이미지 url로 불러오기)
        Glide.with(this).load("https://cdn.pixabay.com/photo/2017/10/12/20/15/photoshop-2845779_1280.jpg").into(imgGlideBasic);

        ImageView imgGlideDrawable = findViewById(R.id.imgGlideDrawable);
        Glide.with(this).load(R.drawable.parachute).into(imgGlideDrawable);

        troubleShooting(imgGlideDrawable);
    }

    private void troubleShooting(ImageView imgGlideDrawable){
        /**
         *  'You cannot start a load for a destroyed activity error' 대처하기
         */
        RequestManager requestManager = Glide.with(this);
        requestManager.load(R.drawable.parachute).into(imgGlideDrawable);

        /**
         *  큰 이미지에서 화질저하가 심해지는 현상 해결하기
         */

        ImageView imgGlideResize = findViewById(R.id.imgGlideResize);
        Glide.with(this).load(R.drawable.parachute).apply(RequestOptions.formatOf(DecodeFormat.PREFER_ARGB_8888)).into(imgGlideResize);
    }
}
