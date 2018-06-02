package com.gandan.android.kotlinfb

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class SettingActivity : AppCompatActivity() {

    var firebaseAuth = FirebaseAuth.getInstance()
    var firebaseUser = firebaseAuth.currentUser
    //lateinit으로 선언만 한다.
    lateinit var dialog : AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        var userEmail : String? = firebaseUser?.email
        var txtUserEmailSetting = findViewById<TextView>(R.id.txtUserEmailSetting)
        var linearOpenSource = findViewById<LinearLayout>(R.id.linearOpenSource)
        var linearLogout = findViewById<LinearLayout>(R.id.linearLogout)
        txtUserEmailSetting.text = userEmail
        //로그아웃 할 건지 묻는 Dialog 생성.
        linearOpenSource.setOnClickListener {
            intent = Intent(this, OpenSourceActivity::class.java)
            startActivity(intent)
        }
        linearLogout.setOnClickListener {

            var positive = DialogInterface.OnClickListener { dialog, which -> logout()  }

            var negative = DialogInterface.OnClickListener{ dialog, which -> dialog.dismiss()}

            dialog = AlertDialog.Builder(this).setTitle("로그아웃 하시겠습니까?").setPositiveButton("아니오", negative).setNegativeButton("네", positive).create()
            dialog.show()
        }
    }

    fun logout(){
        //firebaseAuth 로그아웃.
        firebaseAuth.signOut()
        intent = Intent(this, LoginActivity::class.java)
        //뒤로가기를 했을 때 이전의 내용이 나오는 것을 방지.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
