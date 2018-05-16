package com.gandan.android.kotlinfb

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class SettingActivity : AppCompatActivity() {

    var firebaseAuth = FirebaseAuth.getInstance()
    var firebaseUser = firebaseAuth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        var userEmail : String? = firebaseUser?.email
        var txtUserEmailSetting = findViewById<TextView>(R.id.txtUserEmailSetting)
        var linearOpenSource = findViewById<LinearLayout>(R.id.linearOpenSource)
        var linearLogout = findViewById<LinearLayout>(R.id.linearLogout)
        txtUserEmailSetting.text = userEmail
        linearOpenSource.setOnClickListener {
            intent = Intent(this, OpenSourceActivity::class.java)
            startActivity(intent)
        }
        linearLogout.setOnClickListener {
            firebaseAuth.signOut()
            intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}
