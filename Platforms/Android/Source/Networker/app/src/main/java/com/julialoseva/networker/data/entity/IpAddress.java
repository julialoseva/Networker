package com.julialoseva.networker.data.entity;

import io.realm.RealmObject;

public class IpAddress extends RealmObject {

    private String ip;

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    private long timeStamp;

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
