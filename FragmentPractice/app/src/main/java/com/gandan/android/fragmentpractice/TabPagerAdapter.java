package com.gandan.android.fragmentpractice;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by XPS on 2018-03-02.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
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
