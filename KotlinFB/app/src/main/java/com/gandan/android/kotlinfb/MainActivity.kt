package com.gandan.android.kotlinfb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebaseAuth  = FirebaseAuth.getInstance()
        val firebaseUser = firebaseAuth.currentUser
        var userEmail = firebaseUser?.email
        Log.e("userEmail", userEmail+"")

    }
}
