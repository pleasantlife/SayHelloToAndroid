package com.gandan.android.kotlinfb

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gandan.android.kotlinfb.adapter.MainRecyclerAdapter
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
    private var txtCurrentTime : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDoSetting = findViewById<Button>(R.id.btnDoSetting)
        btnDoSetting.setOnClickListener{
                intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
        }
        txtCurrentTime = findViewById(R.id.txtCurrentTime)

        displayRealTimeClock()
        loadData()
        setRecyclerView()
    }


    //실시간으로 시각을 표시하는 시계를 RxJava를 이용하여 생성.
    private fun displayRealTimeClock() {
        val sdf = SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초", Locale.KOREA)
        sdf.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        var realTimeClock = Observable.interval(1, TimeUnit.SECONDS)
        realTimeClock.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe {
            txtCurrentTime?.text = sdf.format(System.currentTimeMillis())
        }
    }

    private fun loadData() {

    }

    private fun setRecyclerView() {
        val recyclerMain = findViewById<RecyclerView>(R.id.recyclerMain)
        val requestManager = GlideApp.with(this)
        recyclerMain.adapter = MainRecyclerAdapter(this, requestManager)
        recyclerMain.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(v: View?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
