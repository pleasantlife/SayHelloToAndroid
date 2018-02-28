package com.gandan.android.vworldapipractice.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by XPS on 2018-02-28.
 */

public class Item {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("point")
    @Expose
    private Point point;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
