package com.gandan.android.coordinatorpractice.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gandan.android.coordinatorpractice.R;

public class TestRecyclerView extends RecyclerView.Adapter<TestRecyclerView.Holder> {

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.support_simple_spinner_dropdown_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.textView.setText(position+"");
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView textView;

        public Holder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
