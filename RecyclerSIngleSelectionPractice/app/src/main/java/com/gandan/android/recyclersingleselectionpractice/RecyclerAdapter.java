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

import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.MultiSelectorBindingHolder;
import com.bignerdranch.android.multiselector.SingleSelector;
import com.bignerdranch.android.multiselector.SwappingHolder;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {

    Context context;
    List<Item> contact;
    MultiSelector singleSelector;
    int selectedPosition = -1;

    public RecyclerAdapter(Context context, List<Item> contact, MultiSelector singleSelector){
        this.context = context;
        this.contact = contact;
        this.singleSelector = singleSelector;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reycler_item, parent, false);
        return new Holder(view, singleSelector);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        Item item = contact.get(position);
        singleSelector.bindHolder(holder, position, R.layout.reycler_item);

        holder.textName.setText(item.getName());
        if(singleSelector.isSelectable()){
            holder.textName.setTextColor(Color.parseColor("#01cda6"));
            holder.layoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.setSelectable(false);
                    holder.textName.setTextColor(Color.parseColor("#1e1e1e"));
                    notifyDataSetChanged();
                }
            });
        } else{
            holder.textName.setTextColor(Color.parseColor("#1e1e1e"));
            holder.layoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.setSelectable(true);
                    holder.textName.setTextColor(Color.parseColor("#01cda6"));
                    notifyDataSetChanged();
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

    class Holder extends MultiSelectorBindingHolder{

        TextView textName;
        ConstraintLayout layoutItem;
        MultiSelector singleSelector;
        boolean select;

        public Holder(View itemView, MultiSelector singleSelector) {
            super(itemView, singleSelector);
            this.singleSelector = singleSelector;
            textName = itemView.findViewById(R.id.txt_item_name);
            layoutItem = itemView.findViewById(R.id.layout_item);
        }

        @Override
        public void setSelectable(boolean select) {
            this.select = select;
        }

        @Override
        public boolean isSelectable() {
            return select;
        }

        @Override
        public void setActivated(boolean b) {

        }

        @Override
        public boolean isActivated() {
            return false;
        }

    }
}
