package com.gandan.android.diffutil

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(var dummyList: ArrayList<DataModels.DummyModel>) : RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {

    var tracker : SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        /*TODO("not implemented") //To change body of created functions use File | Settings | File Templates.*/
        var view  = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return RecyclerHolder(view)
    }

    override fun getItemCount(): Int {
        /*TODO("not implemented") //To change body of created functions use File | Settings | File Templates.*/
        return dummyList.size
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.)
        holder.nameTxtView.text = dummyList[position].name
        tracker!!.let {
            holder.bind(dummyList[position].name, it.isSelected(position.toLong()))
        }

    }



    inner class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var nameTxtView = itemView.findViewById<TextView>(R.id.itemTextView)!!
        var backGroundLinear = itemView.findViewById<LinearLayout>(R.id.itemLinear)!!

        fun bind(name : String?, isActivated: Boolean = false) {
            nameTxtView.text = name
            itemView.isActivated = isActivated
            if (isActivated) backGroundLinear.setBackgroundColor(Color.BLUE) else backGroundLinear.setBackgroundColor(Color.WHITE)

        }

        fun getItemDetails() : ItemDetailsLookup.ItemDetails<Long> =
                object : ItemDetailsLookup.ItemDetails<Long>() {
                    override fun getPosition(): Int = adapterPosition
                    override fun getSelectionKey(): Long? = itemId
                }
    }
}