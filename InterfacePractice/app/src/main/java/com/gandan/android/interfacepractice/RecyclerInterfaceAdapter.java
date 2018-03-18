package com.gandan.android.interfacepractice;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPS on 2018-03-18.
 */

public class RecyclerInterfaceAdapter extends RecyclerView.Adapter<RecyclerInterfaceAdapter.Holder> {

    Context context;
    List<String> hundredList = new ArrayList<>();
    DataListener dataListener;


    public RecyclerInterfaceAdapter(Context context, List<String> hundredList, DataListener dataListener){
        this.context = context;
        this.hundredList = hundredList;
        //MainActivity에서 this로 넘겨받아 dataListener를 선언함.
        this.dataListener = dataListener;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final String string = hundredList.get(position);
        holder.txtItem.setText(string);
        holder.constraintItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //아이템을 눌렀을 때, 해줄 행동을 선언하는 과정에
                //구현된 인터페이스를 사용하여 데이터를 MainActivity로 전달.
                dataListener.sendData(string);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hundredList.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView txtItem;
        ConstraintLayout constraintItem;

        public Holder(View itemView) {
            super(itemView);
            txtItem = itemView.findViewById(R.id.txtItem);
            constraintItem = itemView.findViewById(R.id.constraintItem);
        }
    }
}
