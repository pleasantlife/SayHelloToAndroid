package com.gandan.android.recyclersingleselectionpractice;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {

    Context context;
    List<Item> contact;
    boolean single;

    public RecyclerAdapter(Context context, List<Item> contact, boolean single){
        this.context = context;
        this.contact = contact;
        this.single = single;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reycler_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        final Item item = contact.get(position);

        holder.textName.setText(item.getName());
        if(item.isSelect()){
            holder.textName.setTextColor(Color.parseColor("#01cda6"));
            holder.textName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   Toast.makeText(context, "이미 선택됨", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            holder.textName.setTextColor(Color.parseColor("#1e1e1e"));
            holder.textName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RxEventBus.getInstance().sendEvent(item);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return contact.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView textName;
        ConstraintLayout layoutItem;
        boolean select;

        public Holder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.txt_item_name);
            layoutItem = itemView.findViewById(R.id.layout_item);
        }
    }
}
