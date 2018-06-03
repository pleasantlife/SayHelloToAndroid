package com.gandan.android.kotlinfb

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private var firebaseAuth = FirebaseAuth.getInstance()
    private var firebaseUser = firebaseAuth.currentUser
    //internal : 같은 모듈 내에서 모두 사용 가능한 변수.
    internal lateinit var intent : Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Thread.sleep(2000)
        firebaseUser.let {
            intent = when(it) {
                null -> Intent(this, LoginActivity::class.java)
                else -> Intent(this, MainActivity::class.java)
            }
            startActivity(intent)
            this.finish()
        }
    }
}
