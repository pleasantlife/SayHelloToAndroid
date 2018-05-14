package com.gandan.android.kotlinfb

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.gandan.android.kotlinfb.R.id.btnDoLogin
import com.gandan.android.kotlinfb.R.id.inputPW
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity()  {

    var firebaseAuth = FirebaseAuth.getInstance()
    var firebaseUser = firebaseAuth.currentUser
    var email : String = ""
    var password : String = ""
    var inputEmail : EditText? = null
    var inputPW : EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        inputEmail = findViewById<EditText>(R.id.inputEmail)
        inputPW = findViewById<EditText>(R.id.inputPW)
        var btnDoLogin = findViewById<Button>(R.id.btnDoLogin)
        var btnDoRegister = findViewById<Button>(R.id.btnDoRegister)

        btnDoLogin.setOnClickListener(this::click)
        btnDoRegister.setOnClickListener(this::click)
    }

    fun click(view : View) {
        if(view is Button) {
            when (view.id) {
                R.id.btnDoLogin -> doLogin()
                R.id.btnDoRegister -> doRegister()
            }
        }
    }


    fun doLogin(){
        email = inputEmail!!.text.toString()
        password = inputPW!!.text.toString()
        if(email != "" && password != ""){
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, {
                task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "로그인 실패!", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, "빠짐없이 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }


    fun doRegister(){
        email = inputEmail!!.text.toString()
        password = inputPW!!.text.toString()
        if(email != "" && password != "") {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, {
                task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "성공!", Toast.LENGTH_SHORT).show()
                } else {
                    Log.e("error", task.result.toString()+"")
                    Toast.makeText(this, "실패!", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, "빠짐없이 입력해주세요!", Toast.LENGTH_SHORT).show()
        }
    }

}

