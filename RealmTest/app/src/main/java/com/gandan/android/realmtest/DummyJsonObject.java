package com.gandan.android.realmtest;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.Required;

public class DummyJsonObject extends RealmObject implements RealmModel {

    public DummyJsonObject() {}

    //Required 어노테이션을 사용하면 null을 허용하지 않게 된다.
    @Required
    private int id;

    private String first_name;

    private String last_name;

    private String email;

    private String gender;

    private String ip_address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }
}
