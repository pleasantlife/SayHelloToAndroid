package com.gandan.android.transitionpractice.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gandan.android.transitionpractice.R;

public class TransitionAdapter extends RecyclerView.Adapter<TransitionAdapter.Holder> {

    Context context;

    public TransitionAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.itemOne.setText(position+"");
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView itemOne;

        public Holder(View itemView) {
            super(itemView);

            itemOne = itemView.findViewById(R.id.txtOne);
        }
    }
}
