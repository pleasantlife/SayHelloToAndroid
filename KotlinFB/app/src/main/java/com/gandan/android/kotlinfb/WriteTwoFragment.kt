package com.gandan.android.kotlinfb


import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.gandan.android.kotlinfb.R.id.inputWriteTwo
import com.google.firebase.storage.FirebaseStorage
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.fragment_write_two.*
import kotlinx.android.synthetic.main.fragment_write_two.view.*
import java.io.File


/**
 * A simple [Fragment] subclass.
 */
class WriteTwoFragment : Fragment() {

    companion object {
        val REQUEST_PHOTO_TWO = 177
    }

    var storageReference = FirebaseStorage.getInstance().getReference("imageTwo")

    lateinit var listener : GetWriteDataListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is GetWriteDataListener){
            listener = context
        }


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imgTwo.setOnClickListener {
            startActivityForResult(Intent(Intent.ACTION_PICK).apply {
                data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            }, REQUEST_PHOTO_TWO)
        }
        RxTextView.textChanges(inputWriteTwo).subscribe {
            listener.contentTwo(it.toString())
            txtCountTwo.text = getString(R.string.char_count, it.length)
            if(it.length >= 120){
                Toast.makeText(context, "120자 이내로 작성해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
        Glide.with(context!!).load(storageReference).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)).apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher_round).apply(RequestOptions.circleCropTransform()))
                .into(imgTwo)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_write_two, container, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == REQUEST_PHOTO_TWO) {
            Glide.with(this).load(data?.data).into(imgTwo)
            File(getRealPath(data)).let {
                listener.imageTwo(it)
            }
            Toast.makeText(context, "사진 선택 완료!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "사진 선택 취소!", Toast.LENGTH_SHORT).show()
        }

    }

    fun getRealPath(data: Intent?) : String{
        var uri = data?.data
        var projection = arrayOf(MediaStore.Images.Media.DATA)
        var cursor = context!!.contentResolver.query(uri, projection, null, null, null)
        var columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(columnIndex)

    }
}// Required empty public constructor
