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
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity()  {

    var firebaseAuth = FirebaseAuth.getInstance()
    var firebaseUser = firebaseAuth.currentUser
    private var email = ""
    private var password  = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(firebaseAuth.currentUser != null){
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        //kotlin Extension을 사용하면 findViewById를 사용하지 않아도 xml과 변수를 연결할 수 있다.
        btnDoLogin.visibility = View.GONE


        //RxBinding으로 아이디가 Email 형식에 맞으면 '로그인하기' 버튼이 나타나고, 아니면 나타나지 않는다.
        inputEmail.textChanges().subscribe{
            if(Patterns.EMAIL_ADDRESS.matcher(inputEmail.text.toString()).matches()){
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
            /*when (view.id) {
                R.id.btnDoLogin -> doLogin()
                R.id.btnDoRegister -> doRegister()
            }*/
            when(view){
                btnDoLogin -> doLogin()
                btnDoRegister -> doRegister()
            }
        }
    }

    //로그인 버튼을 눌렀을 때의 동작을 정의한 함수.
    //함수 내의 파라미터에 16으로 기본값을 넣음.
    //기본값이 있으므로 파라미터에 값을 넣지 않아도 함수를 사용할 수 있다.
    fun doLogin(code : Int = 16){
        if(code == 16) {
            email = inputEmail.text.toString()
            password = inputPW.text.toString()
        }
        if(email != "" && password != ""){
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
                task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "로그인 실패!", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            if(email != "") {
                Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else if(password != ""){
                Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //가입하기 버튼을 눌렀을 때의 동작.
    fun doRegister(){
        email = inputEmail.text.toString()
        password = inputPW.text.toString()
        if(email != "" && password != "") {
            //onCompleteListener에서 자바처럼 new로 Listener가 생성되지 않기 때문에, p0에 this를 넣고, Lambda로 처리한다.
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "성공!", Toast.LENGTH_SHORT).show()
                    doLogin(27)
                } else {
                    Log.e("error", task.result.toString()+"")
                    Toast.makeText(this, "실패!", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "빠짐없이 입력해주세요!", Toast.LENGTH_SHORT).show()
        }
    }

}

