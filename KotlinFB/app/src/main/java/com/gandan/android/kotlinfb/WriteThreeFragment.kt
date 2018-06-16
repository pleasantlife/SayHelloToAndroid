package com.gandan.android.kotlinfb


import android.app.Activity.RESULT_OK
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.fragment_write_three.*
import kotlinx.android.synthetic.main.fragment_write_three.view.*
import java.io.File


/**
 * A simple [Fragment] subclass.
 */
class WriteThreeFragment : Fragment() {

    companion object {
        val REQUEST_PHOTO_THREE = 188
    }

    var firebaseStorage = FirebaseStorage.getInstance()
    var storageReference = firebaseStorage.getReference("imageThree")

    lateinit var listener : GetWriteDataListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is GetWriteDataListener){
            listener = context

        }

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imgThree.setOnClickListener {
            startActivityForResult(Intent(Intent.ACTION_PICK).apply {
                data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            }, REQUEST_PHOTO_THREE)
        }
        RxTextView.textChanges(inputWriteThree).subscribe {
            listener.contentThree(it.toString())
        }
        Glide.with(context!!).load(storageReference).apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher).apply(RequestOptions.centerCropTransform())).into(imgThree)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_write_three, container, false)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == REQUEST_PHOTO_THREE){
            Glide.with(this).load(getRealPath(data)).into(imgThree)
            File(getRealPath(data)).let {
                listener.imageThree(it)
            }
            Toast.makeText(context, "사진 선택 완료!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "사진 선택 취소!", Toast.LENGTH_SHORT).show()
        }
    }

    fun getRealPath(data: Intent?) : String {
        var uri = data?.data
        var projection = arrayOf(MediaStore.Images.Media.DATA)
        var cursor = context!!.contentResolver.query(uri, projection, null, null, null)
        var columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(columnIndex)
    }
}// Required empty public constructor
