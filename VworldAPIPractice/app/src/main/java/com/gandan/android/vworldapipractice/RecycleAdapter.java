package com.gandan.android.vworldapipractice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPS on 2018-02-28.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.Holder> {

    Context context;
    List<String> roadList = new ArrayList();

    public RecycleAdapter(Context context, List<String> roadList){
        this.context = context;
        this.roadList = roadList;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return roadList.size();
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String road = roadList.get(position);
        holder.txtAddress.setText(road);

    }

    class Holder extends RecyclerView.ViewHolder {

        TextView txtAddress;

        public Holder(View itemView) {
            super(itemView);
            txtAddress = itemView.findViewById(R.id.txtAddress);
        }
    }
}
