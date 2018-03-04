package com.gandan.android.fragmentpractice;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by XPS on 2018-03-02.
 */


//프래그먼트의 상태를 나타내고 전달하는 FragmentStatePagerAdapter를 상속받은 커스텀 어댑터를 만든다.

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    //몇 개의 프래그먼트를 사용할 것인지 어댑터에 전달해준다.
    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    //프래그먼트 변수를 생성해준다.
    @Override
    public Fragment getItem(int position) {
        //몇번쨰 아이템인지를 가려내고
        switch(position){
            //아이템에 따라 알맞은 프래그먼트를 불러온다.
            case 0:
                ViewPagerOneFragment oneFragment = new ViewPagerOneFragment();
                return oneFragment;
            case 1:
                ViewPagerTwoFragment twoFragment = new ViewPagerTwoFragment();
                return twoFragment;
            case 2:
                ViewPagerThreeFragment threeFragment = new ViewPagerThreeFragment();
                return threeFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
