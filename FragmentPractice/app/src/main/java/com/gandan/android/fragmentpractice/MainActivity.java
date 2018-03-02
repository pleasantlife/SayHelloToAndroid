package com.gandan.android.fragmentpractice;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    FragmentManager fragmentManager;
    BlankFragment blankFragment;
    Button btnGoViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGoViewPager = findViewById(R.id.btnGoViewPager);
        btnGoViewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(intent);
            }
        });
        fragmentBasic();

    }

    private void fragmentBasic(){

        //프래그먼트 사용을 위해 FragmentManager와 FragmentTransaction 클래스를 생성.

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //Fragment Class를 선언해준다.
        blankFragment = new BlankFragment();
        //XML에서 Fragment의 위치를 지정해주어야 한다.
        fragmentTransaction.add(R.id.blankFragment, blankFragment);
        fragmentTransaction.commit();
    }
}
