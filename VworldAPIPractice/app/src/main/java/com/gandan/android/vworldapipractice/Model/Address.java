package com.gandan.android.vworldapipractice.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by XPS on 2018-02-28.
 */

public class Address {

    @SerializedName("zipcode")
    @Expose
    private String zipcode;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("road")
    @Expose
    private String road;
    @SerializedName("parcel")
    @Expose
    private String parcel;
    @SerializedName("bldnm")
    @Expose
    private String bldnm;

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getParcel() {
        return parcel;
    }

    public void setParcel(String parcel) {
        this.parcel = parcel;
    }

    public String getBldnm() {
        return bldnm;
    }

    public void setBldnm(String bldnm) {
        this.bldnm = bldnm;
    }

}
