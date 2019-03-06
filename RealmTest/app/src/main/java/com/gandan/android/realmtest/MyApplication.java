package com.gandan.android.realmtest;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends AppCompatActivity {

    Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);

        realm = Realm.getDefaultInstance();
        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealmtest.realm").build();
        Realm.setDefaultConfiguration(config);
    }

}
