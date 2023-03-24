package com.example.myapplication.entity;

import java.io.Serializable;

public class Asset implements Serializable {
    private int deviceNum;
    private Float deviceOriginalValue;
    private int newThisMonth;
    private int scrapThisMonth;
    private int dailyMaintain;
    private int secondMaintain;
    private int performanceTest;
    private int meteringRemind;
    private int insuranceRemind;

    public int getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(int deviceNum) {
        this.deviceNum = deviceNum;
    }

    public Float getDeviceOriginalValue() {
        return deviceOriginalValue;
    }

    public void setDeviceOriginalValue(Float deviceOriginalValue) {
        this.deviceOriginalValue = deviceOriginalValue;
    }

    public int getNewThisMonth() {
        return newThisMonth;
    }

    public void setNewThisMonth(int newThisMonth) {
        this.newThisMonth = newThisMonth;
    }

    public int getScrapThisMonth() {
        return scrapThisMonth;
    }

    public void setScrapThisMonth(int scrapThisMonth) {
        this.scrapThisMonth = scrapThisMonth;
    }

    public int getDailyMaintain() {
        return dailyMaintain;
    }

    public void setDailyMaintain(int dailyMaintain) {
        this.dailyMaintain = dailyMaintain;
    }

    public int getSecondMaintain() {
        return secondMaintain;
    }

    public void setSecondMaintain(int secondMaintain) {
        this.secondMaintain = secondMaintain;
    }

    public int getPerformanceTest() {
        return performanceTest;
    }

    public void setPerformanceTest(int performanceTest) {
        this.performanceTest = performanceTest;
    }

    public int getMeteringRemind() {
        return meteringRemind;
    }

    public void setMeteringRemind(int meteringRemind) {
        this.meteringRemind = meteringRemind;
    }

    public int getInsuranceRemind() {
        return insuranceRemind;
    }

    public void setInsuranceRemind(int insuranceRemind) {
        this.insuranceRemind = insuranceRemind;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "deviceNum=" + deviceNum +
                ", deviceOriginalValue=" + deviceOriginalValue +
                ", newThisMonth=" + newThisMonth +
                ", scrapThisMonth=" + scrapThisMonth +
                ", dailyMaintain=" + dailyMaintain +
                ", secondMaintain=" + secondMaintain +
                ", performanceTest=" + performanceTest +
                ", meteringRemind=" + meteringRemind +
                ", insuranceRemind=" + insuranceRemind +
                '}';
    }
}
