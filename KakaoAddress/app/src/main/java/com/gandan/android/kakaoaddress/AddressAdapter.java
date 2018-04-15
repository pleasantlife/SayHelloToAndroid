package com.gandan.android.kakaoaddress;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gandan.android.kakaoaddress.AddressModel.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XPS on 2018-04-14.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.Holder> {

    Context context;
    List<Document> documentList = new ArrayList<>();


    public AddressAdapter(Context context, List<Document> documentList){
        this.context = context;
        this.documentList = documentList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Document document = documentList.get(position);
        holder.txtAddressItem.setText(document.getAddressName());
        holder.txtCityItem.setText(document.getAddress().getRegion1depthName()+"");
    }

    @Override
    public int getItemCount() {
        return documentList.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView txtAddressItem, txtCityItem;

        public Holder(View itemView) {
            super(itemView);
            txtAddressItem = itemView.findViewById(R.id.txtAddressItem);
            txtCityItem = itemView.findViewById(R.id.txtCityItem);
        }
    }
}
