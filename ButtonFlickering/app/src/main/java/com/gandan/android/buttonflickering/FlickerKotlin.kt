package com.gandan.android.buttonflickering

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_flicker_kotlin.*

class FlickerKotlin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flicker_kotlin)

        var observer : Observable<String> = Observable.create { text -> Log.e("observer", text.toString()) }
        observer.subscribe {  }

    }
}
