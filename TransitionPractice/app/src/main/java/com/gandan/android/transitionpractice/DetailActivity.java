package com.gandan.android.transitionpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailActivity extends AppCompatActivity {

    ImageView detailImg;
    TextView txtDetailOne, txtDetailTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        detailImg = findViewById(R.id.detailImg);
        Glide.with(this).load(intent.getStringExtra("images")).apply(RequestOptions.centerCropTransform()).into(detailImg);
        txtDetailOne = findViewById(R.id.txtDetailOne);
        txtDetailOne.setText(intent.getStringExtra("firstTextView"));
        txtDetailTwo = findViewById(R.id.txtDetailTwo);
        txtDetailTwo.setText(intent.getStringExtra("secondTextView"));

    }
}
