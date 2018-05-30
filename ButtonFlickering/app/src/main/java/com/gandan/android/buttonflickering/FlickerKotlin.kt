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

    var count : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flicker_kotlin)
        btnKotlin.setBackgroundColor(Color.parseColor("#ffffff"))
        printColorCode()
    }

    fun printColorCode() {

        var vib : Observable<Unit> = Observable.create { it.onNext(setColor()) }
        vib.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe()

    }

    fun setColor() {

        var rangeHundred =
                if(count == 0){
            (0..99) }
                else {
            (99 downTo 0)
                }
        rangeHundred.forEach{
                Thread.sleep(10)
                when{
                    it == 0 -> {
                        Log.e("zero", it.toString())
                        btnKotlin.setBackgroundColor(Color.parseColor("#0${it}50C1F3"))
                    }
                    it < 10 -> {
                        Log.e("under10", it.toString())
                        Log.e("count", count.toString())
                        btnKotlin.setBackgroundColor(Color.parseColor("#0${it}50C1F3"))
                    }
                    it in 10..98 -> {
                        Log.e("10toNinety", it.toString())
                        btnKotlin.setBackgroundColor(Color.parseColor("#${it}50C1F3"))
                    }
                    it == 99 -> {
                        Log.e("99", it.toString())
                        btnKotlin.setBackgroundColor(Color.parseColor("#${it}50C1F3"))
                        count = if(count == 0){
                            1
                        } else {
                            0
                        }
                    }
                }
        }
        setColor()
    }
}
