package com.gandan.android.realmtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.DynamicRealmObject;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.internal.android.JsonUtils;

public class MainActivity extends AppCompatActivity {

    JSONObject jsonObject;
    JSONArray jsonArray;
    DummyJsonObject dummyJsonObject = new DummyJsonObject();
    RealmList<DummyJsonObject> list = new RealmList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);

        Realm realm = Realm.getDefaultInstance();
        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealmtest.realm").build();
        Realm.setDefaultConfiguration(config);
        //realm.beginTransaction();

        realm.executeTransaction(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {
                JSONArray array = null;
                JSONObject object = null;

                try {
                    array = new JSONArray(DummyJsonText.dummyJson);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for(int i = 0; i < array.length(); i++) {
                    try {
                        object = array.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    list.add(realm.createObjectFromJson(DummyJsonObject.class, object));
                }
            }
        });

        Log.e("list", list.get(2).getEmail()+"");



    }
}
