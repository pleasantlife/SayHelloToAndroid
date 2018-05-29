package com.gandan.android.kotlinfb


import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.fragment_write_one.*
import kotlinx.android.synthetic.main.fragment_write_one.view.*


/**
 * A simple [Fragment] subclass.
 */
class WriteOneFragment : Fragment() {



    lateinit var listener : GetWriteDataListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is GetWriteDataListener){
            listener = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_write_one, container, false)
        /**
         *  kotlin Extension을 쓰면 findViewById 할 필요가 없이 xml과 자동으로 연동해준다!
         *  단, onCreateView 안에서는 view를 지정해줘야 한다.
         */
        //view.txtWriteOne.setOnClickListener{ Log.e("text", txtWriteOne.text.toString())}
        view.imgOne.setOnClickListener {
            var intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            this.startActivityForResult(intent, 166)
        }
        return view
    }

    /**
     *  하지만 여기에서 kotlin Extension을 이용해 xml과 연동하면
     *  view를 지정해줄 필요가 없다!
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("textActivity", txtWriteOne.text.toString()+"")
        txtWriteOne.setOnClickListener{ Log.e("text", txtWriteOne.text.toString())}
        RxTextView.textChanges(inputWriteOne).subscribe { text -> listener.one(text.toString()) }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == 166){
            Glide.with(this).load(data?.data).apply{
                RequestOptions.circleCropTransform()
                RequestOptions.placeholderOf(R.mipmap.ic_launcher)
            }.into(imgOne)
            Log.e("path", getRealData(data))
        } else {
            Toast.makeText(context, "사진 선택 취소!", Toast.LENGTH_SHORT).show()
        }
    }

    fun getRealData(dataIntent : Intent?) : String {
        var uri = dataIntent?.data
        var projection = arrayOf(MediaStore.Images.Media.DATA)
        var cursor = context!!.contentResolver.query(uri, projection, null, null, null)
        var columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(columnIndex)
    }

    /**
     *  view 이외의 로직이 필요할 때 아래의 함수에!
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("onCreate", "writeOne")
        super.onCreate(savedInstanceState)

    }
}// Required empty public constructor
