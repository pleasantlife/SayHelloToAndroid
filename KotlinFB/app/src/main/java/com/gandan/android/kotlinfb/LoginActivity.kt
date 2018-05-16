package com.gandan.android.kotlinfb

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.jakewharton.rxbinding2.widget.textChanges

class LoginActivity : AppCompatActivity()  {

    var firebaseAuth = FirebaseAuth.getInstance()

    private var email = ""
    private var password  = ""
    private var inputEmail : EditText? = null
    private var inputPW : EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(firebaseAuth.currentUser != null){
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        inputEmail = findViewById(R.id.inputEmail)
        inputPW = findViewById(R.id.inputPW)
        var btnDoLogin = findViewById<Button>(R.id.btnDoLogin)
        var btnDoRegister = findViewById<Button>(R.id.btnDoRegister)
        btnDoLogin.visibility = View.GONE


        //RxBinding으로 아이디가 Email 형식에 맞으면 '로그인하기' 버튼이 나타나고, 아니면 나타나지 않는다.
        inputEmail?.textChanges()?.subscribe{
            if(Patterns.EMAIL_ADDRESS.matcher(inputEmail?.text.toString()).matches()){
               btnDoLogin.visibility = View.VISIBLE
           } else {
               btnDoLogin.visibility = View.GONE
           }
       }

        btnDoLogin.setOnClickListener(this::click)
        btnDoRegister.setOnClickListener(this::click)
    }

    fun click(view : View) {
        if(view is Button) {
            //when == switch
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
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
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

