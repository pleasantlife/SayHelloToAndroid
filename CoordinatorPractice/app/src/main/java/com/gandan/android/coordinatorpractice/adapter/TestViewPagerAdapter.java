package com.gandan.android.coordinatorpractice.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gandan.android.coordinatorpractice.R;
import com.gandan.android.coordinatorpractice.fragment.TestOneFragment;
import com.gandan.android.coordinatorpractice.fragment.TestTwoFragment;

public class TestViewPagerAdapter extends FragmentStatePagerAdapter {

    Context context;

    public TestViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new TestOneFragment();
            case 1:
                return new TestTwoFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return context.getString(R.string.tab_layout_left);
            case 1:
                return context.getString(R.string.tab_layout_right);
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
