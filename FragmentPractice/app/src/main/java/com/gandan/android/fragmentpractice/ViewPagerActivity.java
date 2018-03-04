package com.gandan.android.fragmentpractice;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    TabPagerAdapter tabPagerAdapter;
    FragmentRecyclerAdapter fragmentRecyclerAdapter;
    List<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        //뷰페이저와 탭 레이아웃을 결합하기 위해서 TabLayout과 ViewPager를 xml에 미리 등록해주었다.
        //등록한 TabLayout과 Viewpager는 이들을 사용할 액티비티에서 선언해야 한다.
        tabLayout = findViewById(R.id.tabLayout);
        //ViewPager를 사용하려면 어댑터가 필요하다.
        tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), 3);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(tabPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                //(뷰페이저에 속한) 리사이클러뷰를 둔 프래그먼트의 데이터를 변화시키려면, 해당 프래그먼트가 속한 액티비티에서 직접 조정해줘야 한다.
                viewPager.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
