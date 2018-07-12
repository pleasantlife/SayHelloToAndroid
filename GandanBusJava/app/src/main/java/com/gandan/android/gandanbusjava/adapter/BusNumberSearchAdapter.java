package com.gandan.android.gandanbusjava.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gandan.android.gandanbusjava.R;
import com.gandan.android.gandanbusjava.model.BusRoute;

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
    }

    @Override
    public int getItemCount() {
        return busRouteList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView textBusRouteNumber;

        public Holder(View itemView) {
            super(itemView);

            textBusRouteNumber = itemView.findViewById(R.id.textBusRouteNumber);
        }
    }
}
