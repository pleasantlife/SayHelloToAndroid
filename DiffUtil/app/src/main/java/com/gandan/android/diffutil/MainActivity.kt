package com.gandan.android.diffutil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list : ArrayList<DataModels.DummyModel> = arrayListOf()
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        var recyclerAdapter = RecyclerAdapter(list)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
        }


        for(i in 0..300){
            var model : DataModels.DummyModel = DataModels.DummyModel(i, "아이템 $i")
            list.add(model)
        }
        recyclerView.adapter!!.notifyDataSetChanged()

        var selectionTracker : SelectionTracker<Long> = SelectionTracker.Builder(
            "selection-demo",
            recyclerView,
            StableIdKeyProvider(recyclerView),
            MyItmDetailsLookup(recyclerView),
            StorageStrategy.createLongStorage()).withSelectionPredicate(
            SelectionPredicates.createSelectSingleAnything()
        ).build()


        recyclerAdapter.tracker = selectionTracker
    }
}
