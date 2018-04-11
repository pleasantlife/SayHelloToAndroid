package com.gandan.android.fcmpratice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by XPS on 2018-03-29.
 */

//보내는 메세지 데이터 클래스

public class FCMSend {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("registration_ids")
    @Expose
    private List<String> to = null;
    @SerializedName("android_channel_id")
    @Expose
    private String androidChannelId;
    @SerializedName("notification")
    @Expose
    private Notification notification;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public String getAndroidChannelId() {
        return androidChannelId;
    }

    public void setAndroidChannelId(String androidChannelId) {
        this.androidChannelId = androidChannelId;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
