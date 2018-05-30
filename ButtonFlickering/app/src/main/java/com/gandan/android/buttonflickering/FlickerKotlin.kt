package com.gandan.android.buttonflickering

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_flicker_kotlin.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class FlickerKotlin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flicker_kotlin)
        btnKotlin.setBackgroundColor(Color.parseColor("#ffffff"))
        printColorCode()
    }

    fun printColorCode() {

        var vib : Observable<Unit> = Observable.create { it.onNext(setColor()) }
        vib.delay(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe()

    }

    fun setColor() {
        var count : Int = 0
        var rangeHundred : IntRange = if(count == 0){
            (0..99)
        } else {
            ((99 downTo 0) as IntRange)
        }
        rangeHundred.forEach{
            Thread.sleep(100)
            when{
                it == 0 -> {
                    count = 0
                    btnKotlin.setBackgroundColor(Color.parseColor("#0050C1F3"))
                    //setColor()
                }
                it < 10 -> {
                    btnKotlin.setBackgroundColor(Color.parseColor("#0${it}50C1F3"))
                }
                it in 11..98 -> {
                    btnKotlin.setBackgroundColor(Color.parseColor("#${it}50C1F3"))
                }
                it == 99 -> {
                    count = 1
                    btnKotlin.setBackgroundColor(Color.parseColor("#9950C1F3"))
                    setColor()
                }
            }
        }
    }
}
