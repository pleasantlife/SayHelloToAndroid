package com.gandan.android.gandanbus

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class LiveBusRecyclerAdapter(var context : Context) : RecyclerView.Adapter<LiveBusRecyclerAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        var view = LayoutInflater.from(parent.context).inflate(R.layout.bus_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount() = 3

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}