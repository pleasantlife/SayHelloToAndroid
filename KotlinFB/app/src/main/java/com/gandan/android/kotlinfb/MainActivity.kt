package com.gandan.android.kotlinfb

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

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

        val btnDoLogout = findViewById<Button>(R.id.btnDoLogout)
        btnDoLogout.setOnClickListener(this::onClick)
    }

    //로그아웃!
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnDoLogout -> {
                firebaseAuth.signOut()
                var intent : Intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
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
