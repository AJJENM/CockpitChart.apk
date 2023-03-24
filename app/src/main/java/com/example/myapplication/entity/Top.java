package com.example.myapplication.entity;

import java.io.Serializable;

public class Top implements Serializable {
    private String deviceName;
    private String rRPerson;
    private String status;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getrRPerson() {
        return rRPerson;
    }

    public void setrRPerson(String rRPerson) {
        this.rRPerson = rRPerson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Top{" +
                "deviceName='" + deviceName + '\'' +
                ", rRPerson='" + rRPerson + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
