package com.gandan.android.fragmentpractice;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPS on 2018-03-04.
 */

public class FragmentRecyclerAdapter extends RecyclerView.Adapter<FragmentRecyclerAdapter.Holder> {

    List<String> fragmentList = new ArrayList<>();
    Activity activity;

    public FragmentRecyclerAdapter(Activity activity, List<String> fragmentList){
        this.activity = activity;
        this.fragmentList = fragmentList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String fragment = fragmentList.get(position);
        holder.txtItem.setText(fragment);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }



    class Holder extends RecyclerView.ViewHolder {

        TextView txtItem;

        public Holder(View itemView) {
            super(itemView);
            txtItem = itemView.findViewById(R.id.txtItem);
        }
    }
}
