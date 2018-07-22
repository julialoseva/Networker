package com.julialoseva.networker.data.engine;

import com.julialoseva.networker.data.entity.IpAddressInformation;

import java.util.Collection;

import io.realm.Realm;
import io.realm.RealmResults;

public class Store {

    private static final Store sharedInstance = new Store();

    public static Store getInstance() {
        return sharedInstance;
    }

    private Realm realm;

    public Store() {
        this.realm = Realm.getDefaultInstance();
    }

    public IpAddressInformation createIpAddressInformation(
            String ip,
            long timeStamp
    ) {
        realm.beginTransaction();
        IpAddressInformation ipAddressInformation = this.realm.createObject(IpAddressInformation.class);
        ipAddressInformation.setIp(ip);
        ipAddressInformation.setTimeStamp(timeStamp);
        realm.commitTransaction();
        return ipAddressInformation;
    }

    public Collection<IpAddressInformation> getAllIpAddressInformation() {
        return this.realm
                .where(IpAddressInformation.class)
                .findAll();
    }

    public void removeAllIpAddressInformation() {
        RealmResults<IpAddressInformation> results = this.realm
                .where(IpAddressInformation.class)
                .findAll();
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }
}
