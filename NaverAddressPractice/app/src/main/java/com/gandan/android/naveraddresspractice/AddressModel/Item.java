package com.gandan.android.naveraddresspractice.AddressModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by XPS on 2018-04-16.
 */

public class Item {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("addrdetail")
    @Expose
    private Addrdetail addrdetail;
    @SerializedName("isRoadAddress")
    @Expose
    private Boolean isRoadAddress;
    @SerializedName("point")
    @Expose
    private Point point;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Addrdetail getAddrdetail() {
        return addrdetail;
    }

    public void setAddrdetail(Addrdetail addrdetail) {
        this.addrdetail = addrdetail;
    }

    public Boolean getIsRoadAddress() {
        return isRoadAddress;
    }

    public void setIsRoadAddress(Boolean isRoadAddress) {
        this.isRoadAddress = isRoadAddress;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

}
