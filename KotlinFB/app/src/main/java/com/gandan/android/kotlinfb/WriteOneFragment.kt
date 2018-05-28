package com.gandan.android.kotlinfb


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_write_one.*
import kotlinx.android.synthetic.main.fragment_write_one.view.*


/**
 * A simple [Fragment] subclass.
 */
class WriteOneFragment : Fragment() {

    lateinit var listener : GetWriteOneDataListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is GetWriteOneDataListener){
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
        RxTextView.textChanges(inputWriteOne).subscribe { text -> listener.test(text.toString()) }


    }

    /**
     *  view 이외의 로직이 필요할 때 아래의 함수에!
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("onCreate", "writeOne")
        super.onCreate(savedInstanceState)

    }
}// Required empty public constructor
