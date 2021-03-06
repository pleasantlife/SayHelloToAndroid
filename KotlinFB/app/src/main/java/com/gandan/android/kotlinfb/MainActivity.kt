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
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var pressedTime : Long = 0
    private var zero : Long = 0
    private var firebaseAuth = FirebaseAuth.getInstance()
    private var firebaseDatabase = FirebaseDatabase.getInstance()
    private var databaseReference = firebaseDatabase.reference
    lateinit var requestManager : GlideRequests
    private lateinit var clock : Disposable

    private var stringList = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestManager = GlideApp.with(this)

        //실시간으로 시각을 표시하는 시계를 RxJava를 이용하여 생성.
        clock = Observable.interval(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe {
            txtCurrentTime.text = SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초", Locale.KOREA).apply { timeZone = TimeZone.getTimeZone("Asia/Seoul") }.format(System.currentTimeMillis())
        }

        btnDoSetting.setOnClickListener { startActivity(Intent(this, SettingActivity::class.java))}
        btnGoWrite.setOnClickListener { startActivity(Intent(this@MainActivity, WriteActivity::class.java)) }
        databaseReference.ref.child("userdb").child(firebaseAuth.currentUser?.uid).addValueEventListener(initValueListener())
        setData()
        loadCompliments()
    }


    fun loadCompliments(){

        databaseReference.ref.child("userInfodb").child(firebaseAuth!!.uid).child("compliments").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.e("error loading", p0!!.message.toString()+"")
            }

            override fun onDataChange(p0: DataSnapshot?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.e("dataSnapshot", p0!!.childrenCount.toString()+"")
                p0!!.children.forEach {
                    next -> Log.e("next", next.value.toString()+"")
                }
            }

        })
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
                Log.e("DataSnapshot Userdb", dataSnapshot.toString()+"")
                var userDb = dataSnapshot?.getValue(UserDb::class.java)
                Log.e("email", userDb?.email+"")
                Log.e("uid", userDb?.uid+"")
                stringList.add(userDb?.email+"")
                stringList.add(userDb?.uid+"")
                var filteredList = stringList.map { s -> "테스트 "+s }
                Log.e("filteredList", filteredList.toString()+"")
                filteredList.apply {
                    with(recyclerMain){
                        adapter = MainRecyclerAdapter(this@MainActivity, requestManager, filteredList)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                }
            }

        }
        return valueEventListener
    }


    //Firebase Realtime Database에 Email 주소와 uid 등록.
    private fun setData(){
        var userId = UserDb()
        userId.email = firebaseAuth.currentUser?.email
        userId.uid = firebaseAuth.currentUser?.uid
        databaseReference.ref.child("userdb").child(firebaseAuth.currentUser?.uid).setValue(userId)

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

    override fun onDestroy() {
        super.onDestroy()
        //시간을 나타내는 Observable이 더이상 메모리를 점유하지 않도록 정리!
        clock.dispose()
    }
}
