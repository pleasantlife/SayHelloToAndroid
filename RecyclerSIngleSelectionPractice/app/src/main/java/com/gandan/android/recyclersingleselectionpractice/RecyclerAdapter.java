package com.gandan.android.recyclersingleselectionpractice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {

    Context context;
    List<Item> contact;
    boolean headerFlag = false;

    public RecyclerAdapter(Context context, List<Item> contact){
        this.context = context;
        this.contact = contact;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        headerFlag = viewType == 0;
        View view;
        if(viewType == 0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_header, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reycler_item, parent, false);
        }
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if(position == 0){
        }else{
            Item item = contact.get(position-1);
            holder.textName.setText(item.getName());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return contact.size()+1;
    }

    class Holder extends RecyclerView.ViewHolder{

        boolean isHeader = headerFlag;
        TextView textName;

        public Holder(View itemView) {
            super(itemView);

            if(!isHeader){
                textName = itemView.findViewById(R.id.txt_item_name);
            }
        }
    }
}
