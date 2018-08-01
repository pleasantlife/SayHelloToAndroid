package com.gandan.android.gandanbusjava.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gandan.android.gandanbusjava.R;
import com.gandan.android.gandanbusjava.model.BusLocationList;
import com.gandan.android.gandanbusjava.model.BusRouteStation;

import java.util.List;

public class LiveBusRecyclerAdapter extends RecyclerView.Adapter<LiveBusRecyclerAdapter.Holder> {

    Context context;
    List<BusLocationList> liveBusList;
    List<BusRouteStation> busRouteStationList;

    public LiveBusRecyclerAdapter(Context context, /*List<BusLocationList> liveBusList*/ List<BusRouteStation> busRouteStationList, List<BusLocationList> liveBusList){
        this.context = context;
        this.liveBusList = liveBusList;
        this.busRouteStationList = busRouteStationList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_recycler_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
       BusRouteStation busRouteStation = busRouteStationList.get(position);
       holder.busEndBus.setVisibility(View.INVISIBLE);
       holder.busLow.setVisibility(View.INVISIBLE);
       if(position == 0){
           holder.busStaName.setText("<기점> "+ busRouteStation.getStationName());
       } else if(position == busRouteStationList.size() - 1) {
           holder.busStaName.setText("<종점> " + busRouteStation.getStationName());
       } else {
           if(busRouteStationList.get(position-1).getUpDown().equals("정") && busRouteStationList.get(position).getUpDown().equals("역")){
               holder.busStaName.setText("<회차> " + busRouteStation.getStationName());
           } else {
               holder.busStaName.setText(busRouteStation.getStationName());
           }
       }
        holder.busStationSeq.setText(busRouteStation.getStationOrder());

       if(liveBusList.size() != 0){
           for(BusLocationList busLocationList : liveBusList){
               if(busLocationList.getStationId().equals(busRouteStation.getStationId())){
                   holder.busEndBus.setText(busLocationList.getPlateNo()+"");
                   holder.busEndBus.setVisibility(View.VISIBLE);
                   if(busLocationList.getLowPlate().equals("1")){
                       holder.busLow.setVisibility(View.VISIBLE);
                   }
               }
           }
       }
    }

    @Override
    public int getItemCount() {
        return busRouteStationList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView busStaName, busLow, busEndBus, busStationSeq;

        public Holder(View itemView) {
            super(itemView);

            busStaName = itemView.findViewById(R.id.busStaName);
            busLow = itemView.findViewById(R.id.busLow);
            busEndBus = itemView.findViewById(R.id.busEndBus);
            busStationSeq = itemView.findViewById(R.id.busStationSeq);
        }
    }
}
