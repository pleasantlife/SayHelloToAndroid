package com.gandan.android.gandanbusjava.model;

public class BusRouteStation {

    //버스노선 고유 아이디 (ex : 성우 300 -> 200000006)
    private String routeId;
    //버스정류장 고유 아이디 (ex : 오산역,오산 터미널 -> 223000126)
    private String stationId;
    //버스 운행 방향 (정방향/역방향)
    private String upDown;
    //정류장 순서 (00번째 정류장)
    private String stationOrder;
    //노선 번호
    private String routeNumber;
    //정류장 명
    private String stationNumber;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getUpDown() {
        return upDown;
    }

    public void setUpDown(String upDown) {
        this.upDown = upDown;
    }

    public String getStationOrder() {
        return stationOrder;
    }

    public void setStationOrder(String stationOrder) {
        this.stationOrder = stationOrder;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }
}
