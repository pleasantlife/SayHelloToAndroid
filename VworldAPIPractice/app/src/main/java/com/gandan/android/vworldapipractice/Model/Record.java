package com.gandan.android.vworldapipractice.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by XPS on 2018-02-28.
 */

public class Record {

    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("current")
    @Expose
    private String current;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }
}
