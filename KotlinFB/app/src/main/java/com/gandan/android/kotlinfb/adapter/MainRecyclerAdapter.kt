package com.gandan.android.kotlinfb.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.gandan.android.kotlinfb.GlideApp
import com.gandan.android.kotlinfb.R

/**
 * Created by XPS on 2018-05-17.
 */
class MainRecyclerAdapter(var context : Context, var requestManager: RequestManager) : RecyclerView.Adapter<MainRecyclerAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        holder.txtContentsItem?.text = "내용"
        holder.txtTitleItem?.text = "타이틀"
        requestManager.load("https://cdn.pixabay.com/photo/2018/05/12/16/45/paper-3393903__480.jpg").apply(RequestOptions.centerCropTransform()).into(holder.imgBackItem!!)
        holder.cardViewItem?.setOnClickListener {
            Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return 10
    }

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var cardViewItem = itemView?.findViewById<CardView>(R.id.cardViewItem)
        var txtTitleItem = itemView?.findViewById<TextView>(R.id.txtTitleItem)
        var txtContentsItem = itemView?.findViewById<TextView>(R.id.txtContentsItem)
        var imgBackItem = itemView?.findViewById<ImageView>(R.id.imgBackItem)

    }
}