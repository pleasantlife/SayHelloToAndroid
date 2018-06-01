package com.gandan.android.kotlinfb

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private var firebaseAuth = FirebaseAuth.getInstance()
    private var firebaseUser = firebaseAuth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Thread.sleep(2000)
        if(firebaseUser === null){
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            this.finish()
        } else {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}
