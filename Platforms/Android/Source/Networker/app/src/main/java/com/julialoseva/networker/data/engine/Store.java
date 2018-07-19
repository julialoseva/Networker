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

    public IpAddressInformation createIpAddressInformation(
            String ip,
            long timeStamp
    ) {
        IpAddressInformation ipAddressInformation = new IpAddressInformation();
        return ipAddressInformation;
    }

    public Collection<IpAddressInformation> getAllIpAddressInformation() {
        return this.realm.where(IpAddressInformation.class)
                .findAll();
    }

    public void removeAllIpAddressInformation() {
        RealmResults<IpAddressInformation> results =
                realm.where(IpAddressInformation.class)
                .findAll();

        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }
}
