package com.gandan.android.kotlinfb

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    var firebaseAuth = FirebaseAuth.getInstance()
    var firebaseUser = firebaseAuth.currentUser
    //lateinit으로 선언만 한다.
    //lateinit var dialog : AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        var txtUserEmailSetting = findViewById<TextView>(R.id.txtUserEmailSetting)
        txtUserEmailSetting.text = firebaseUser?.email
        //로그아웃 할 건지 묻는 Dialog 생성.
        linearOpenSource.setOnClickListener { startActivity(Intent(this, OpenSourceActivity::class.java)) }
        linearLogout.setOnClickListener {
            //var positive = DialogInterface.OnClickListener { dialog, which -> logout()  }
            //var negative = DialogInterface.OnClickListener{ dialog, which -> dialog.dismiss()}
            AlertDialog.Builder(this).setTitle("로그아웃 하시겠습니까?")
                    .setPositiveButton("아니오", { dialog, which -> dialog.dismiss()})
                    .setNegativeButton("네", { dialog, which -> logout()})
                    .create().apply { show() }
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
