package com.gandan.android.kotlinfb.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.gandan.android.kotlinfb.GetWriteOneDataListener
import com.gandan.android.kotlinfb.WriteOneFragment
import com.gandan.android.kotlinfb.WriteThreeFragment
import com.gandan.android.kotlinfb.WriteTwoFragment

/**
 * Created by XPS on 2018-05-23.
 */
class FragmentWriteAdapter(fm: FragmentManager?, counts : Int, listener : GetWriteOneDataListener) : FragmentStatePagerAdapter(fm) {

    var tabCount = counts

    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()
        when(position){
            0 -> fragment = WriteOneFragment()
            1 -> fragment = WriteTwoFragment()
            2 -> fragment = WriteThreeFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return tabCount
    }
}