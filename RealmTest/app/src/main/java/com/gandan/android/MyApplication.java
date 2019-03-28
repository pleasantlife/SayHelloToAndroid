package com.gandan.android;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        Realm.getDefaultInstance();

        //Builder type으로 Realm config 가능!
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealmtest.realm")
                //스키마 버전을 지정하여 서로 다른 config를 설정할 수 있음.
                .schemaVersion(2)
                .build();

        Realm.setDefaultConfiguration(config);

        //똑같은 파일에 스키마 버전만 다르게 할 수도 있음.
        RealmConfiguration configNew = new RealmConfiguration.Builder()
                .name("myrealmtest.realm")
                .schemaVersion(16)
                .build();

        //아예 다른 설정을 적용할 수도 있다.
        RealmConfiguration configSub = new RealmConfiguration.Builder()
                .name("myrealmsub.realm")
                .schemaVersion(3)
                .build();
    }
}
