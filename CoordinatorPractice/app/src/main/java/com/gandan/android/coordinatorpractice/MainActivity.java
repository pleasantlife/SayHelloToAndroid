package com.gandan.android.coordinatorpractice;

import android.animation.Animator;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

import com.gandan.android.coordinatorpractice.adapter.TestViewPagerAdapter;

import static android.support.v4.view.ViewPager.SCROLL_STATE_DRAGGING;

public class MainActivity extends AppCompatActivity implements ScrollListenerInterface {

    TestViewPagerAdapter testViewPagerAdapter;
    NestedScrollView nestedScrollView;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nestedScrollView = findViewById(R.id.nestedScrollView);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        testViewPagerAdapter = new TestViewPagerAdapter(getSupportFragmentManager(), this);


        viewPager.setAdapter(testViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager, false);


        for (int i = 0; i < 2; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            View tabView = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            tabView.requestLayout();
            View view = LayoutInflater.from(this).inflate(R.layout.tab_test, null);

            tab.setCustomView(view);
            //tab.setText(i + "");
        }


    }

    @Override
    public void scroll(int firstVisibleItemInRecyclerView) {

        if(firstVisibleItemInRecyclerView >= 0 && firstVisibleItemInRecyclerView < 2){
            tabLayout.setVisibility(View.VISIBLE);
        }
        if(firstVisibleItemInRecyclerView > 2){
            tabLayout.setVisibility(View.GONE);
        }
    }
}
