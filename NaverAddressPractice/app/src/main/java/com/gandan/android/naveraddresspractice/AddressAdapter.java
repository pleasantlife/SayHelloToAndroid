package com.gandan.android.naveraddresspractice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gandan.android.naveraddresspractice.AddressModel.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPS on 2018-04-17.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.Holder> {

    Context context;
    List<Item> itemList = new ArrayList<>();

    public AddressAdapter(Context context, List<Item> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Item item = itemList.get(position);
        holder.txtAllAddress.setText(item.getAddress()+"");
        holder.txtProvince.setText(item.getAddrdetail().getSido()+"");
        holder.txtCity.setText(item.getAddrdetail().getSigugun()+"");
        holder.txtDong.setText(item.getAddrdetail().getDongmyun()+"");
        holder.txtRest.setText(item.getAddrdetail().getRest()+"");
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView txtAllAddress, txtProvince, txtCity, txtDong, txtRest;

        public Holder(View itemView) {
            super(itemView);

            txtAllAddress = itemView.findViewById(R.id.txtAllAddress);
            txtProvince = itemView.findViewById(R.id.txtProvince);
            txtCity = itemView.findViewById(R.id.txtCity);
            txtDong = itemView.findViewById(R.id.txtDong);
            txtRest = itemView.findViewById(R.id.txtRest);

        }
    }
}
