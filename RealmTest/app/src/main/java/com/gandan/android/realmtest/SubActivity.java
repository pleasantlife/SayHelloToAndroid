package com.gandan.android.realmtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

public class SubActivity extends AppCompatActivity {

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        realm = Realm.getDefaultInstance();

        for(DummyJsonObject result : realm.where(DummyJsonObject.class).equalTo("gender", "female").findAll()){
            Log.e("result", result.getEmail()+"");
        }
    }
}
