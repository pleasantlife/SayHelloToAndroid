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
import kotlinx.android.synthetic.main.recycler_item.view.*

/**
 * Created by XPS on 2018-05-17.
 */
class MainRecyclerAdapter(var context : Context, var requestManager: RequestManager, var lists : List<String>) : RecyclerView.Adapter<MainRecyclerAdapter.Holder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        var stringText = lists[position]
        holder.itemView.txtTitleItem.text = "타이틀"
        holder.itemView.txtContentsItem.text = stringText
        requestManager.load("https://cdn.pixabay.com/photo/2018/05/12/16/45/paper-3393903__480.jpg").apply(RequestOptions.centerCropTransform()).into(holder.itemView.imgBackItem)
        holder.itemView.cardViewItem.setOnClickListener { Toast.makeText(context, stringText, Toast.LENGTH_SHORT).show() }
    }

    override fun getItemCount(): Int = lists.size /* {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return lists.size
    }*/

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}