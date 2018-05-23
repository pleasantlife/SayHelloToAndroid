package com.gandan.android.kotlinfb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.gandan.android.kotlinfb.adapter.FragmentWriteAdapter

class WriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)
        var viewPagerWrite = findViewById<ViewPager>(R.id.viewPagerWrite)
        viewPagerWrite.adapter = FragmentWriteAdapter(supportFragmentManager, 3)


    }
}
