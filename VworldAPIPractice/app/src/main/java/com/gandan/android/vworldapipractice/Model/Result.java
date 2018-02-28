package com.gandan.android.vworldapipractice.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by XPS on 2018-02-28.
 */

public class Result {
    @SerializedName("crs")
    @Expose
    private String crs;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public String getCrs() {
        return crs;
    }

    public void setCrs(String crs) {
        this.crs = crs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
