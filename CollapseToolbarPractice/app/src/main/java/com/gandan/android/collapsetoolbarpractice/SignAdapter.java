package com.gandan.android.collapsetoolbarpractice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class SignAdapter extends RecyclerView.Adapter<SignAdapter.Holder> {

    Context context;
    List<SignData> signDataList = new ArrayList<>();

    public SignAdapter(Context context, List<SignData> signDataList){
        this.context = context;
        this.signDataList = signDataList;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_style, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        SignData signData = signDataList.get(position);
        Glide.with(context).load(signData.getSignImage()).apply(RequestOptions.centerCropTransform()).into(holder.signImage);
        holder.textSignName.setText(signData.getSignName());
        holder.textSignLocation.setText(signData.getSignLocation());
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView signImage;
        TextView textSignName, textSignLocation;

        public Holder(View itemView) {
            super(itemView);

            signImage = itemView.findViewById(R.id.signImage);
            textSignLocation = itemView.findViewById(R.id.textSignLocation);
            textSignName = itemView.findViewById(R.id.textSignName);

        }
    }
}
