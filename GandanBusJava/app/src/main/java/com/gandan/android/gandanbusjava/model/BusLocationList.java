package com.gandan.android.gandanbusjava.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;


public class BusLocationList {

    @Element(name = "endBus")
    private String endBus;

    @Element(name = "plateType")
    private String plateType;

    @Element(name = "remainSeatCnt")
    private String remainSeatCnt;

    @Element(name = "stationSeq")
    private String stationSeq;

    @Element(name = "stationId")
    private String stationId;

    @Element(name = "routeId")
    private String routeId;

    @Element(name = "lowPlate")
    private String lowPlate;

    @Element(name = "plateNo")
    private String plateNo;

    public String getEndBus ()
    {
        return endBus;
    }

    public void setEndBus (String endBus)
    {
        this.endBus = endBus;
    }

    public String getPlateType ()
    {
        return plateType;
    }

    public void setPlateType (String plateType)
    {
        this.plateType = plateType;
    }

    public String getRemainSeatCnt ()
    {
        return remainSeatCnt;
    }

    public void setRemainSeatCnt (String remainSeatCnt)
    {
        this.remainSeatCnt = remainSeatCnt;
    }

    public String getStationSeq ()
    {
        return stationSeq;
    }

    public void setStationSeq (String stationSeq)
    {
        this.stationSeq = stationSeq;
    }

    public String getStationId ()
    {
        return stationId;
    }

    public void setStationId (String stationId)
    {
        this.stationId = stationId;
    }

    public String getRouteId ()
    {
        return routeId;
    }

    public void setRouteId (String routeId)
    {
        this.routeId = routeId;
    }

    public String getLowPlate ()
    {
        return lowPlate;
    }

    public void setLowPlate (String lowPlate)
    {
        this.lowPlate = lowPlate;
    }

    public String getPlateNo ()
    {
        return plateNo;
    }

    public void setPlateNo (String plateNo)
    {
        this.plateNo = plateNo;
    }

}
