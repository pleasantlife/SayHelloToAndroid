package com.gandan.android.kotlinfb

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private var pressedTime : Long = 0
    private var zero : Long = 0
    var firebaseAuth  = FirebaseAuth.getInstance()
    val firebaseUser = firebaseAuth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var userEmail = firebaseUser?.email
        Log.e("userEmail", userEmail+"")

        val btnDoSetting = findViewById<Button>(R.id.btnDoSetting)
        btnDoSetting.setOnClickListener{
                intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
        }

        var txtCurrentTime = findViewById<TextView>(R.id.txtCurrentTime)
        var sdf = SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초", Locale.KOREAN)
        sdf.timeZone = TimeZone.getTimeZone("Asia/Seoul")

        var realTimeClock = Observable.interval(1000, TimeUnit.MILLISECONDS)
        realTimeClock.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe {
            txtCurrentTime.text = sdf.format(System.currentTimeMillis())
        }



    }

    //로그아웃!
    override fun onClick(v: View?) {
        /*when(v?.id){
            R.id.btnDoLogout -> {
                firebaseAuth.signOut()
                var intent : Intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }*/
    }




    //뒤로 가기를 3초 이내에 두 번 누르면 앱을 종료한다.
    override fun onBackPressed() {

        if(pressedTime == zero){
            pressedTime = System.currentTimeMillis()
            Toast.makeText(this, "한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        } else {
            if(System.currentTimeMillis() - pressedTime > 3000){
                pressedTime = zero
                Toast.makeText(this, "한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
            } else {
                super.onBackPressed()
                finish()
            }
        }
    }
}
