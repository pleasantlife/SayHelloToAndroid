package com.gandan.android.kotlinfb


import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
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
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_write_one.*
import kotlinx.android.synthetic.main.fragment_write_one.view.*
import kotlinx.android.synthetic.main.fragment_write_three.*


/**
 * A simple [Fragment] subclass.
 */
class WriteOneFragment : Fragment() {

    //java의 public static 변수처럼 사용하려면 'companion object'로 변수를 선언해야 한다!
    companion object {
        val REQUEST_PHOTO_ONE = 166
    }


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
            this.startActivityForResult(Intent(Intent.ACTION_PICK).apply {
                data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            }, REQUEST_PHOTO_ONE)
        }
        //EditText에 포커스 가는 것 막기!
        //view.requestFocus()
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
        RxTextView.textChanges(inputWriteOne).subscribe { listener.one(it.toString()) }
        //string.xml에 넣어둔 string을 쓰려면 getString을 이용해야한다!
        RxTextView.textChanges(inputWriteOne).subscribe {
            txtCountOne.text = getString(R.string.char_count, it.length)
            listener.one(it.toString())
            if(it.length >= 120){
                Toast.makeText(context, "120자 이내로 작성해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == REQUEST_PHOTO_ONE){
            Glide.with(this).load(data?.data).apply(RequestOptions.circleCropTransform()).apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher)).into(imgOne)
            //뒷배경에 등록된 이미지를 띄우고, 이를 블러처리 하는 코드.
            Glide.with(this).load(data?.data).apply(RequestOptions.bitmapTransform(BlurTransformation(50, 3))).into(imgBlurOne)
            imgBlurOne.visibility = View.VISIBLE
            Log.e("path", getRealData(data))
            Toast.makeText(context, "사진 선택이 완료되었습니다!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "사진 선택 취소!", Toast.LENGTH_SHORT).show()
        }
    }

    //업로드를 위해 real path를 string값으로 반환하는 함수.
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
