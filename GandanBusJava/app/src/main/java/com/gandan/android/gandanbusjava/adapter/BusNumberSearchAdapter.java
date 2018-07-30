package com.gandan.android.gandanbusjava.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gandan.android.gandanbusjava.BusRouteActivity;
import com.gandan.android.gandanbusjava.R;
import com.gandan.android.gandanbusjava.RouteIdListener;
import com.gandan.android.gandanbusjava.model.BusRoute;
import com.gandan.android.gandanbusjava.model.BusRouteStation;

import java.io.Serializable;
import java.util.List;

public class BusNumberSearchAdapter extends RecyclerView.Adapter<BusNumberSearchAdapter.Holder> {

    Context context;
    List<BusRoute> busRouteList;

    public BusNumberSearchAdapter(Context context, List<BusRoute> busRouteList){
        this.context = context;
        this.busRouteList = busRouteList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bus_route, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        BusRoute route = busRouteList.get(position);
        holder.textBusRouteNumber.setText(route.getRouteNumber());
        Log.e("district", route.getRegionName()+"");
        holder.textBusType.setText(route.getRegionName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BusRouteActivity.class);
                intent.putExtra("busId", route.getRouteId()+"");
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return busRouteList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView textBusRouteNumber, textBusType;

        public Holder(View itemView) {
            super(itemView);

            textBusRouteNumber = itemView.findViewById(R.id.textBusRouteNumber);
            textBusType = itemView.findViewById(R.id.textBusType);
        }
    }
}
