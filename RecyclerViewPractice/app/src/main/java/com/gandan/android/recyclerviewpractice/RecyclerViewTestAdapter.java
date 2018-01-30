package com.gandan.android.recyclerviewpractice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPS on 2018-01-26.
 */


//리사이클러와 데이터의 아이템 별 뷰를 연결해주는 어댑터.
public class RecyclerViewTestAdapter extends RecyclerView.Adapter<RecyclerViewTestHolder> {


    Context context;
    List<String> dummyList = new ArrayList<>();

    public RecyclerViewTestAdapter(Context context, List<String> dummyList){
        this.context = context;
        this.dummyList = dummyList;
    }


    @Override
    public RecyclerViewTestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new RecyclerViewTestHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewTestHolder holder, int position) {
        String dummy = dummyList.get(position);
        holder.textItem.setText(dummy);
    }

    @Override
    public int getItemCount() {
        return dummyList.size();
    }
}
