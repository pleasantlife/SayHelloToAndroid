package com.gandan.android.gandanbusjava.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;


public class MsgBody {

    @ElementList(name = "busLocationList", inline = true)
    private ArrayList<BusLocationList> busLocationList = new ArrayList<>();

    public ArrayList<BusLocationList> getBusLocationList() {
        return busLocationList;
    }

    public void setBusLocationList(ArrayList<BusLocationList> busLocationList) {
        this.busLocationList = busLocationList;
    }
}
