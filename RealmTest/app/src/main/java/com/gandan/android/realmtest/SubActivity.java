package com.gandan.android.realmtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class SubActivity extends MyApplication {

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Realm.init(this);

        realm = Realm.getDefaultInstance();
        /*RealmConfiguration config = new RealmConfiguration.Builder().name("myrealmtest.realm").build();
        Realm.setDefaultConfiguration(config);*/

        for(DummyJsonObject result : realm.where(DummyJsonObject.class).equalTo("gender", "female").findAll()){
            Log.e("result", result.getEmail()+"");
        }

        //필드에 대한 값이 존재하는 경우만 골라서 빼낼 수도 있음!
        RealmResults<DummyJsonObject> unique = realm.where(DummyJsonObject.class).distinct("gender").findAll();

        for(DummyJsonObject object : unique){
            Log.e("object", object.getEmail()+"");
        }

        /*RealmResults<DummyJsonObject> linear = realm.where(DummyJsonObject.class).between("id", 1, 5).equalTo("gender", "Male")
                .findAll();

        for(DummyJsonObject object : linear){
            Log.d("linear", object.getGender()+" "+object.getEmail());
        }*/

        /**
         *  Realm 쓰기
         */

        RealmResults<DummyJsonObject> all = realm.where(DummyJsonObject.class).findAll();
        int id = 0;

        id = all.size();

        realm.beginTransaction();
        DummyJsonObject realmWriteObject = realm.createObject(DummyJsonObject.class);
        realmWriteObject.setEmail("samsung@galaxy.com");
        realmWriteObject.setFirst_name("Jae Yoong");
        realmWriteObject.setLast_name("Lee");
        realmWriteObject.setGender("Male");
        realmWriteObject.setIp_address("1.1.1.1");
        realmWriteObject.setId(id);
        realm.commitTransaction();

        RealmResults<DummyJsonObject> samsung = realm.where(DummyJsonObject.class).equalTo("email", "samsung@galaxy.com").findAll();

        Log.e("samsung", samsung.get(0).getEmail() + "");



    }
}
