package com.gandan.android.kotlinfb

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.gandan.android.kotlinfb.adapter.FragmentWriteAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_write.*

class WriteActivity : AppCompatActivity(), GetWriteDataListener {

    var WRITE_EX_STORAGE_OK = 166

    var firebaseDatabase = FirebaseDatabase.getInstance()
    var databaseReference = firebaseDatabase.reference
    var firebaseUser = FirebaseAuth.getInstance().currentUser

    var complimentOne : String = ""
    var complimentTwo : String = ""
    var complimentThree : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)
        //ViewPager를 쓰기 위해 별도의 어댑터 클래스를 만들어 연결하였음.
        viewPagerWrite.adapter = FragmentWriteAdapter(supportFragmentManager, 3, this)
        permissionCheck()
        btnDoWrite.setOnClickListener { nullCheck() }
    }

    private fun permissionCheck(){
        var permission = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(permission[0]) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(permission, WRITE_EX_STORAGE_OK)
            }
        } else {

        }
    }

    override fun one(thingOne : String) {
        Log.e("String!!!", thingOne+"")
        complimentOne = thingOne
    }

    override fun two(thingTwo: String) {
        complimentTwo = thingTwo
        Log.e("complimentTwo", complimentTwo)
    }

    override fun three(thingThree : String) {
        complimentThree = thingThree
    }

    fun nullCheck(){
        when {
            complimentOne == "" -> makeToast(0)
            complimentTwo == "" -> makeToast(1)
            //complimentThree == "" -> makeToast(2)
            else -> upload()
        }
    }

    fun makeToast(currentItem : Int){
        Toast.makeText(this, "${currentItem+1}번이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
        viewPagerWrite.currentItem = currentItem
    }

    fun upload(){
        var uploadClass = UploadWritten(complimentOne, complimentTwo, complimentThree)
        databaseReference.ref.child(firebaseUser!!.uid).child("userdb").child("uploadDbTest").setValue(uploadClass).addOnCompleteListener{
            if(it.isSuccessful) {
                Toast.makeText(this, "업로드 완료!", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "업로드 실패!", Toast.LENGTH_SHORT).show()
        }
    }

    data class UploadWritten(var first : String, var second : String, var third : String)
}
