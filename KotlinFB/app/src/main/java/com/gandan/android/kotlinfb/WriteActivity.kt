package com.gandan.android.kotlinfb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.gandan.android.kotlinfb.adapter.FragmentWriteAdapter
import kotlinx.android.synthetic.main.activity_write.*

class WriteActivity : AppCompatActivity(), GetWriteDataListener {

    var complimentOne : String = ""
    var complimentTwo : String = ""
    var complimentThree : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)
        //ViewPager를 쓰기 위해 별도의 어댑터 클래스를 만들어 연결하였음.
        viewPagerWrite.adapter = FragmentWriteAdapter(supportFragmentManager, 3, this)
        btnDoWrite.setOnClickListener { nullCheck() }
    }

    override fun one(thing: String) {
        Log.e("String!!!", thing+"")
        complimentOne = thing
    }

    override fun two(thingTwo: String) {
        complimentTwo = thingTwo
    }

    override fun three(thingThree : String) {
        complimentThree = thingThree
    }

    fun nullCheck(){
        when {
            complimentOne == "" -> makeToast(0)
            complimentTwo == "" -> makeToast(1)
            complimentThree == "" -> makeToast(2)
            else -> Toast.makeText(this, "모든 항목 입력 완료.", Toast.LENGTH_SHORT).show()
        }
    }

    fun makeToast(currentItem : Int){
        Toast.makeText(this, "$currentItem+1"+"번", Toast.LENGTH_SHORT).show()
        viewPagerWrite.currentItem = currentItem
    }
}
