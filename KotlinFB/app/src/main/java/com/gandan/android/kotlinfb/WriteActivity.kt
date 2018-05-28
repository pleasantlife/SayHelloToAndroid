package com.gandan.android.kotlinfb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gandan.android.kotlinfb.adapter.FragmentWriteAdapter
import kotlinx.android.synthetic.main.activity_write.*

class WriteActivity : AppCompatActivity(), GetWriteOneDataListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)
        //ViewPager를 쓰기 위해 별도의 어댑터 클래스를 만들어 연결하였음.
        viewPagerWrite.adapter = FragmentWriteAdapter(supportFragmentManager, 3, this)

    }

    override fun test(thing: String) {
        Log.e("String!!!", thing+"")
    }
}
