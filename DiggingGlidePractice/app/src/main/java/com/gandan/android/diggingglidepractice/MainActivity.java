package com.gandan.android.diggingglidepractice;

import android.graphics.BlurMaskFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;

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
        //RequestOptions에 여러가지 속성을 한번에 적용할 수 있음.
        //여기에서는 화질저하 부분 해결과 함께 모서리를 140px만큼 둥글게 하는 처리를 적용.
        Glide.with(this).load("https://cdn.pixabay.com/photo/2017/10/12/20/15/photoshop-2845779_1280.jpg")
                .apply(RequestOptions.formatOf(DecodeFormat.PREFER_ARGB_8888)
                        .bitmapTransform(new RoundedCorners(140))).into(imgGlideResize);


        ImageView imgGlideBlur = findViewById(R.id.imgGlideBlur);
        Glide.with(this).load("https://cdn.pixabay.com/photo/2017/10/12/20/15/photoshop-2845779_1280.jpg")
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(60)))
                .into(imgGlideBlur);



        MultiTransformation multiTransformation = new MultiTransformation(
                new RoundedCorners(120),
                new BlurTransformation(60),
                new CenterCrop()
        );

        ImageView imgGlideMulti = findViewById(R.id.imgGlideMulti);
        Glide.with(this).load("https://cdn.pixabay.com/photo/2017/10/12/20/15/photoshop-2845779_1280.jpg")
                .apply(RequestOptions.bitmapTransform(multiTransformation))
                .into(imgGlideMulti);

    }
}
