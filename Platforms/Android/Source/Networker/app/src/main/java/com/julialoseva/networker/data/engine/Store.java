package com.julialoseva.networker.data.engine;

import com.julialoseva.networker.data.entity.IpAddress;

import java.util.Collection;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class Store {

    private static final Store sharedInstance = new Store();

    public static Store getInstance() {
        return sharedInstance;
    }

    private Realm realm;

    public Store() {
        this.realm = Realm.getDefaultInstance();
    }

    public IpAddress createIpAddressInformation(
            String ip,
            String providerName,
            long timeStamp
    ) {
        realm.beginTransaction();
        IpAddress ipAddress = this.realm.createObject(IpAddress.class);
        ipAddress.setIp(ip);
        ipAddress.setProviderName(providerName);
        ipAddress.setTimestamp(timeStamp);
        realm.commitTransaction();
        return ipAddress;
    }

    public Collection<IpAddress> getAllIpAddressesSortedByTimestamp(boolean inDescendingOrder) {
        return this.realm
                .where(IpAddress.class)
                .sort("timestamp", inDescendingOrder ? Sort.DESCENDING : Sort.ASCENDING)
                .findAll();
    }

    public void removeAllIpAddressInformation() {
        RealmResults<IpAddress> results = this.realm
                .where(IpAddress.class)
                .findAll();
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }
}
