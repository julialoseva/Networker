package com.julialoseva.networker.app;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class NetworkerApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
