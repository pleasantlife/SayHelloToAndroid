package com.gandan.android.gandanbusjava.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gandan.android.gandanbusjava.R;
import com.gandan.android.gandanbusjava.model.BusLocationList;

import java.util.List;

public class LiveBusRecyclerAdapter extends RecyclerView.Adapter<LiveBusRecyclerAdapter.Holder> {

    Context context;
    List<BusLocationList> liveBusList;

    public LiveBusRecyclerAdapter(Context context, List<BusLocationList> liveBusList){
        this.context = context;
        this.liveBusList = liveBusList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_recycler_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        BusLocationList locationList = liveBusList.get(position);
        holder.busPlateNo.setText(locationList.getPlateNo());
        if(locationList.getEndBus().equals("1")){
            holder.busEndBus.setVisibility(View.VISIBLE);
        } else {
            holder.busEndBus.setVisibility(View.GONE);
        }
        if(locationList.getLowPlate().equals("1")){
            holder.busLow.setVisibility(View.VISIBLE);
        } else {
            holder.busLow.setVisibility(View.GONE);
        }
        holder.busStationSeq.setText(locationList.getStationSeq()+"번째 정류장");
    }

    @Override
    public int getItemCount() {
        return liveBusList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView busPlateNo, busLow, busEndBus, busStationSeq;

        public Holder(View itemView) {
            super(itemView);

            busPlateNo = itemView.findViewById(R.id.busPlateNo);
            busLow = itemView.findViewById(R.id.busLow);
            busEndBus = itemView.findViewById(R.id.busEndBus);
            busStationSeq = itemView.findViewById(R.id.busStationSeq);
        }
    }
}
