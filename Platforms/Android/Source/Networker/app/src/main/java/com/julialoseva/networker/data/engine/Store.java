package com.julialoseva.networker.data.engine;

import com.julialoseva.networker.data.entity.IpSnapshot;

import java.util.ArrayList;
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

    public Collection<IpSnapshot> getAllIpSnapshotsSortedByTimestamp(boolean inDescendingOrder) {
        return this.realm
                .where(IpSnapshot.class)
                .sort("timestamp", inDescendingOrder ? Sort.DESCENDING : Sort.ASCENDING)
                .findAll();
    }

    public IpSnapshot createIpAddressInformation(
            String ip,
            String providerName,
            long timeStamp
    ) {
        realm.beginTransaction();
        IpSnapshot ipSnapshot = this.realm.createObject(IpSnapshot.class);
        ipSnapshot.setIp(ip);
        ipSnapshot.setProviderName(providerName);
        ipSnapshot.setTimestamp(timeStamp);
        realm.commitTransaction();
        return ipSnapshot;
    }

    public void removeAllIpAddressInformation() {
        RealmResults<IpSnapshot> results = this.realm
                .where(IpSnapshot.class)
                .findAll();
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public IpSnapshot[] getSnapshotsByIp(String ip) {
        Collection<IpSnapshot> allSnapshots = this.realm
                .where(IpSnapshot.class)
                .findAll();

        ArrayList<IpSnapshot> filteredSnapshots = new ArrayList<>();
        for (IpSnapshot ipSnapshot : allSnapshots) {
            if (ipSnapshot.getIp() == ip) {
                filteredSnapshots.add(
                        ipSnapshot
                );
            }
        }

        return filteredSnapshots.toArray(
                new IpSnapshot[] {}
        );
    }

    public IpSnapshot[] getSnapshotsByProvider(String provider) {
        Collection<IpSnapshot> allSnapshots = this.realm
                .where(IpSnapshot.class)
                .findAll();

        ArrayList<IpSnapshot> filteredSnapshots = new ArrayList<>();
        for (IpSnapshot ipSnapshot : allSnapshots) {
            if (ipSnapshot.getProviderName() == provider) {
                filteredSnapshots.add(
                        ipSnapshot
                );
            }
        }

        return filteredSnapshots.toArray(
                new IpSnapshot[] {}
        );
    }
}
