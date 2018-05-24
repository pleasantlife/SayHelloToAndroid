package com.gandan.android.kotlinfb


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


/**
 * A simple [Fragment] subclass.
 */
class WriteOneFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_write_one, container, false)
        var txtWriteOne = view.findViewById<TextView>(R.id.txtWriteOne)
        txtWriteOne.setOnClickListener { Log.e("text", txtWriteOne.text.toString()) }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("onCreate", "writeOne")
        super.onCreate(savedInstanceState)
    }
}// Required empty public constructor
