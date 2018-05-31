package com.gandan.android.buttonflickering

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_flicker_kotlin.*
import java.util.concurrent.TimeUnit

/**
 *  버튼의 색상이 바뀌도록 하는 예제 (코틀린 사용)
 */

class FlickerKotlin : AppCompatActivity() {

    var count : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flicker_kotlin)
        btnKotlin.setBackgroundColor(Color.parseColor("#ffffff"))
        printColorCode()
    }

    fun printColorCode() {
        Observable.interval(10, TimeUnit.MILLISECONDS).take(100).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe {
            when {
                it < 10 -> btnKotlin.setBackgroundColor(Color.parseColor("#0${it}50C1F3"))
                it in 10..98 -> btnKotlin.setBackgroundColor(Color.parseColor("#${it}50C1F3"))
                it.toInt() == 99 -> {
                    btnKotlin.setBackgroundColor(Color.parseColor("#9950C1F3"))
                    printMinusColorCode()
                }
            }
        }
    }

    fun printMinusColorCode() {
        Observable.interval(10, TimeUnit.MILLISECONDS).take(100).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe {
            when {
                it.toInt() == 0 -> btnKotlin.setBackgroundColor(Color.parseColor("#9950C1F3"))
                it.toInt() in 1..90 -> btnKotlin.setBackgroundColor(Color.parseColor("#${100-it}50C1F3"))
                it in 91..98 -> btnKotlin.setBackgroundColor(Color.parseColor("#0${100-it}50C1F3"))
                it.toInt() == 99 -> {
                    btnKotlin.setBackgroundColor(Color.parseColor("#0150C1F3"))
                    printColorCode()
                }
            }
        }
    }
}
