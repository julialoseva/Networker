package com.julialoseva.networker.tools;

import android.support.annotation.NonNull;

import com.julialoseva.networker.data.engine.Store;
import com.julialoseva.networker.data.entity.IpSnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IpAnalyticsManager {

    private String[] getIpList() {
        ArrayList<String> ipAddresses = new ArrayList<>();
        Collection<IpSnapshot> ipSnapshots = Store.getInstance()
                .getAllIpAddressesSortedByTimestamp(
                        true
                );

        for (IpSnapshot snapshot : ipSnapshots) {
            ipAddresses.add(
                    snapshot.getIp()
            );
        }

        return ipAddresses.toArray(
                new String[] {}
        );
    }

    private String[] getProvidersList() {
        ArrayList<String> providers = new ArrayList<>();
        Collection<IpSnapshot> ipSnapshots = Store.getInstance()
                .getAllIpAddressesSortedByTimestamp(
                        true
                );

        for (IpSnapshot snapshot : ipSnapshots) {
            providers.add(
                    snapshot.getProviderName()
            );
        }

        return providers.toArray(
                new String[] {}
        );
    }
}
