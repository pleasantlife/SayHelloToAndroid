package com.gandan.android.realmtest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends MyApplication {

    JSONObject jsonObject;
    JSONArray jsonArray;
    DummyJsonObject dummyJsonObject = new DummyJsonObject();
    RealmList<DummyJsonObject> list = new RealmList<>();
    Button goSubActivity;
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
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
                //realm.commitTransaction();
            }


        });




        Log.e("list", list.get(2).getEmail()+"");

        RealmResults<DummyJsonObject> results = realm.where(DummyJsonObject.class).equalTo("gender", "Female").findAll();

        RealmResults<DummyJsonObject> resultsTwo = realm.where(DummyJsonObject.class).contains("email", ".com").findAll();

        //논리연산
        //realm.where(DummyJsonObject.class).greaterThan("id", 3).beginGroup().equalTo("gender", "Female").endGroup().findAll();

        Log.e("results", results.get(0).getLast_name() + results.get(0).getFirst_name() + results.get(0).getGender());
        for(DummyJsonObject object : resultsTwo) {
            Log.e("resultsTow", object.getLast_name() + object.getFirst_name() + object.getEmail());
        }

        goSubActivity = findViewById(R.id.goSubActivity);
        goSubActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
            }
        });


        //Realm Transition 취소
        //realm.cancelTransition();

        //삭제
        RealmResults<DummyJsonObject> resultsThree = realm.where(DummyJsonObject.class).greaterThan("id", 3).beginGroup().equalTo("gender", "Female").endGroup().findAll();

        if(resultsThree.size() > 0) {
            //Deprecated
            //resultsThree.remove(0);

            //Use these!
            //resultsThree.deleteFromRealm(0);
            //resultsThree.deleteAllFromRealm();
            //resultsThree.deleteFirstFromRealm();
            //resultsThree.deleteLastFromRealm();
        }




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
