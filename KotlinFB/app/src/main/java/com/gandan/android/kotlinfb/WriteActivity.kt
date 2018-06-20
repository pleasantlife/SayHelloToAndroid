package com.gandan.android.kotlinfb

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.gandan.android.kotlinfb.R.id.viewPagerWrite
import com.gandan.android.kotlinfb.adapter.FragmentWriteAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_write.*
import java.io.File

class WriteActivity : AppCompatActivity(), GetWriteDataListener {

    var WRITE_EX_STORAGE_OK = 166

    var firebaseDatabase = FirebaseDatabase.getInstance()
    var databaseReference = firebaseDatabase.reference
    var firebaseUser = FirebaseAuth.getInstance().currentUser
    var firebaseStorage = FirebaseStorage.getInstance()
    //괄호안에 넣은 String이 파일명이 된다!!
    var imageOne = firebaseStorage.getReference("imageOne")
    var imageTwo = firebaseStorage.getReference("imageTwo")
    var imageThree = firebaseStorage.getReference("imageThree")
    var written = ArrayList<UploadWritten>()

    lateinit var complimentOne : String
    lateinit var complimentTwo : String
    lateinit var complimentThree : String
    lateinit var complimentImageOne : File
    lateinit var complimentImageTwo : File
    lateinit var complimentImageThree : File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)
        //ViewPager를 쓰기 위해 별도의 어댑터 클래스를 만들어 연결하였음.
        viewPagerWrite.adapter = FragmentWriteAdapter(supportFragmentManager, 3, this)
        //Fragment의 상태를 저장하지 못해서, 상태가 날아가버리는 것을 방지!
        viewPagerWrite.offscreenPageLimit = 3
        //사진을 가져와야 하기 때문에 메모리 읽기/쓰기 권한이 필요하다.
        permissionCheck()
        btnDoWrite.setOnClickListener{ nullCheck() }
        loadCompliments()
    }

    private fun permissionCheck(){
        var permission = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(permission[0]) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(permission, WRITE_EX_STORAGE_OK)
            }
        } else {
            Toast.makeText(this, "권한 획득에 실패하여 사진을 등록할 수 없습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun contentOne(thingOne : String) {
        Log.e("String!!!", thingOne+"")
        complimentOne = thingOne
    }

    override fun contentTwo(thingTwo: String) {
        complimentTwo = thingTwo
        Log.e("complimentTwo", complimentTwo)
    }

    override fun contentThree(thingThree : String) {
        complimentThree = thingThree
    }

    override fun imageOne(fileOne: File) {
        complimentImageOne = fileOne
    }

    override fun imageTwo(fileTwo: File) {
        complimentImageTwo = fileTwo
    }

    override fun imageThree(fileThree: File) {
        complimentImageThree = fileThree
    }

    fun loadCompliments(){

        databaseReference.ref.child("userdb").child(firebaseUser!!.uid).child("compliments").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.e("dataSnapshot", p0!!.childrenCount.toString()+"")
                p0!!.children.forEach {
                    next -> Log.e("next", next.value.toString()+"")
                }
            }

        })
    }


    fun nullCheck(){
        when {
            complimentOne == "" -> makeToast(0, "내용")
            complimentTwo == "" -> makeToast(1, "내용")
            complimentThree == "" -> makeToast(2, "내용")
            //initlized 되었는지를 check하는 방법!!
            !::complimentImageOne.isInitialized -> makeToast(0, "사진")
            !::complimentImageTwo.isInitialized -> makeToast(1, "사진")
            !::complimentImageThree.isInitialized -> makeToast(2, "사진")
            else -> uploadTexts()
        }
    }

    fun makeToast(currentItem : Int, type : String){
        Toast.makeText(this, "${currentItem+1}번 ${type}이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
        viewPagerWrite.currentItem = currentItem
    }



    fun uploadTexts(){

        var newUpload = UploadWritten(complimentOne, complimentTwo, complimentThree)
        written.add(newUpload)
        databaseReference.ref.child("userdb").child(firebaseUser!!.uid).child("compliments").setValue(written).addOnCompleteListener{
            if(it.isSuccessful) {
                Toast.makeText(this, "업로드 완료!", Toast.LENGTH_SHORT).show()
                uploadImages()
                //이미지를 업로드하라고 한 뒤, Back을 해도 오류가 발생하지 않고, 업로드가 잘 된다!
                onBackPressed()
            }
        }.addOnFailureListener{ error ->
            Log.e("error when upload", error.message+"")
            Toast.makeText(this, "업로드 실패!", Toast.LENGTH_SHORT).show()
        }
    }

    fun uploadImages(){
        //첫번째 이미지
        imageOne.putFile(Uri.fromFile(complimentImageOne)).addOnCompleteListener{
            task -> Log.e("urlOne", task.result.downloadUrl.toString()+"")
        }
        //두번쨰 이미지
        imageTwo.putFile(Uri.fromFile(complimentImageTwo)).addOnCompleteListener{
            task -> Log.e("urlTwo", task.result.downloadUrl.toString()+"")

        }
        //세번째 이미지
        imageThree.putFile(Uri.fromFile(complimentImageThree)).addOnCompleteListener{
            task -> Log.e("urlThree", task.result.downloadUrl.toString()+"")
        }
    }




    data class UploadWritten(var first : String, var second : String, var third : String)
}
