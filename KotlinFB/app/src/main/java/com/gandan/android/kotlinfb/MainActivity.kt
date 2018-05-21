package com.gandan.android.kotlinfb

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.gandan.android.kotlinfb.Model.UserDb
import com.gandan.android.kotlinfb.adapter.MainRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var pressedTime : Long = 0
    private var zero : Long = 0
    var firebaseAuth = FirebaseAuth.getInstance()
    var firebaseDatabase = FirebaseDatabase.getInstance()
    var databaseReference = firebaseDatabase.reference
    var recyclerMain : RecyclerView? = null
    private var txtCurrentTime : TextView? = null
    private var stringList = ArrayList<String>()


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
        setData()
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

    //Firebase Realtime Database에서 로그인 한 유저의 정보 가져오기.
    private fun initValueListener() : ValueEventListener{
        var valueEventListener = object : ValueEventListener{
            override fun onCancelled(error: DatabaseError?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.e("error in Loading", error?.message+"")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.e("DataSnapshot", dataSnapshot.toString()+"")
                var userDb = dataSnapshot?.getValue(UserDb::class.java)
                Log.e("email", userDb?.email+"")
                Log.e("uid", userDb?.uid+"")
                stringList.add(userDb?.email+"")
                stringList.add(userDb?.uid+"")
                var filteredList = stringList.map { s -> "테스트 "+s }
                Log.e("filteredList", filteredList.toString()+"")
                setRecyclerView(filteredList)


            }

        }
        return valueEventListener
    }


    //Firebase Realtime Database에 Email 주소와 uid 등록.
    private fun setData(){
        var email = firebaseAuth.currentUser?.email
        var uid = firebaseAuth.currentUser?.uid
        var userId = UserDb()
        userId.email = email
        userId.uid = uid
        databaseReference.ref.child("userdb").child(uid).setValue(userId)

    }


    private fun loadData() {
        databaseReference.ref.child("userdb").child(firebaseAuth.currentUser?.uid).addValueEventListener(initValueListener())
    }

    private fun setRecyclerView(lists : List<String>) {
        recyclerMain = findViewById<RecyclerView>(R.id.recyclerMain)
        val requestManager = GlideApp.with(this)
        recyclerMain!!.adapter = MainRecyclerAdapter(this, requestManager, lists)
        recyclerMain!!.layoutManager = LinearLayoutManager(this)
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
