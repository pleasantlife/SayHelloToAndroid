package com.gandan.android.kotlinfb


import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gandan.android.kotlinfb.R.id.inputWriteTwo
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.fragment_write_two.*
import kotlinx.android.synthetic.main.fragment_write_two.view.*


/**
 * A simple [Fragment] subclass.
 */
class WriteTwoFragment : Fragment() {

    companion object {
        val REQUEST_PHOTO_TWO = 177
    }

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
        RxTextView.textChanges(inputWriteTwo).subscribe { text -> listener.two(text.toString()) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_write_two, container, false)

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == REQUEST_PHOTO_TWO) {

        } else {
            Toast.makeText(context, "사진 선택 취소!", Toast.LENGTH_SHORT).show()
        }

    }
}// Required empty public constructor
