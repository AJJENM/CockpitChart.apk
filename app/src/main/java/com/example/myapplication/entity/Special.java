package com.example.myapplication.entity;

import java.io.Serializable;

public class Special implements Serializable {
    private int meteringNum;
    private double meteringOriginalValue;
    private double meteringNormalRate;
    private int specialNum;
    private double specialOriginalValue;
    private double specialNormalRate;
    private int emergencyNum;
    private double emergencyOriginalValue;
    private double emergencyNormalRate;
    private int radiationNum;
    private double radiationOriginalValue;
    private double radiationNormalRate;
    private int lifeSupportNum;
    private double lifeSupportOriginalValue;
    private double lifeSupportNormalRate;
    private double lifeSupportFaultRate;
    private int largeNum;
    private double largeOriginalValue;
    private double largeNormalRate;
    private double largeFaultRate;

    @Override
    public String toString() {
        return "Special{" +
                "meteringNum=" + meteringNum +
                ", meteringOriginalValue=" + meteringOriginalValue +
                ", meteringNormalRate=" + meteringNormalRate +
                ", specialNum=" + specialNum +
                ", specialOriginalValue=" + specialOriginalValue +
                ", specialNormalRate=" + specialNormalRate +
                ", emergencyNum=" + emergencyNum +
                ", emergencyOriginalValue=" + emergencyOriginalValue +
                ", emergencyNormalRate=" + emergencyNormalRate +
                ", radiationNum=" + radiationNum +
                ", radiationOriginalValue=" + radiationOriginalValue +
                ", radiationNormalRate=" + radiationNormalRate +
                ", lifeSupportNum=" + lifeSupportNum +
                ", lifeSupportOriginalValue=" + lifeSupportOriginalValue +
                ", lifeSupportNormalRate=" + lifeSupportNormalRate +
                ", lifeSupportFaultRate=" + lifeSupportFaultRate +
                ", largeNum=" + largeNum +
                ", largeOriginalValue=" + largeOriginalValue +
                ", largeNormalRate=" + largeNormalRate +
                ", largeFaultRate=" + largeFaultRate +
                '}';
    }

    public int getMeteringNum() {
        return meteringNum;
    }

    public void setMeteringNum(int meteringNum) {
        this.meteringNum = meteringNum;
    }

    public double getMeteringOriginalValue() {
        return meteringOriginalValue;
    }

    public void setMeteringOriginalValue(double meteringOriginalValue) {
        this.meteringOriginalValue = meteringOriginalValue;
    }

    public double getMeteringNormalRate() {
        return meteringNormalRate;
    }

    public void setMeteringNormalRate(double meteringNormalRate) {
        this.meteringNormalRate = meteringNormalRate;
    }

    public int getSpecialNum() {
        return specialNum;
    }

    public void setSpecialNum(int specialNum) {
        this.specialNum = specialNum;
    }

    public double getSpecialOriginalValue() {
        return specialOriginalValue;
    }

    public void setSpecialOriginalValue(double specialOriginalValue) {
        this.specialOriginalValue = specialOriginalValue;
    }

    public double getSpecialNormalRate() {
        return specialNormalRate;
    }

    public void setSpecialNormalRate(double specialNormalRate) {
        this.specialNormalRate = specialNormalRate;
    }

    public int getEmergencyNum() {
        return emergencyNum;
    }

    public void setEmergencyNum(int emergencyNum) {
        this.emergencyNum = emergencyNum;
    }

    public double getEmergencyOriginalValue() {
        return emergencyOriginalValue;
    }

    public void setEmergencyOriginalValue(double emergencyOriginalValue) {
        this.emergencyOriginalValue = emergencyOriginalValue;
    }

    public double getEmergencyNormalRate() {
        return emergencyNormalRate;
    }

    public void setEmergencyNormalRate(double emergencyNormalRate) {
        this.emergencyNormalRate = emergencyNormalRate;
    }

    public int getRadiationNum() {
        return radiationNum;
    }

    public void setRadiationNum(int radiationNum) {
        this.radiationNum = radiationNum;
    }

    public double getRadiationOriginalValue() {
        return radiationOriginalValue;
    }

    public void setRadiationOriginalValue(double radiationOriginalValue) {
        this.radiationOriginalValue = radiationOriginalValue;
    }

    public double getRadiationNormalRate() {
        return radiationNormalRate;
    }

    public void setRadiationNormalRate(double radiationNormalRate) {
        this.radiationNormalRate = radiationNormalRate;
    }

    public int getLifeSupportNum() {
        return lifeSupportNum;
    }

    public void setLifeSupportNum(int lifeSupportNum) {
        this.lifeSupportNum = lifeSupportNum;
    }

    public double getLifeSupportOriginalValue() {
        return lifeSupportOriginalValue;
    }

    public void setLifeSupportOriginalValue(double lifeSupportOriginalValue) {
        this.lifeSupportOriginalValue = lifeSupportOriginalValue;
    }

    public double getLifeSupportNormalRate() {
        return lifeSupportNormalRate;
    }

    public void setLifeSupportNormalRate(double lifeSupportNormalRate) {
        this.lifeSupportNormalRate = lifeSupportNormalRate;
    }

    public double getLifeSupportFaultRate() {
        return lifeSupportFaultRate;
    }

    public void setLifeSupportFaultRate(double lifeSupportFaultRate) {
        this.lifeSupportFaultRate = lifeSupportFaultRate;
    }

    public int getLargeNum() {
        return largeNum;
    }

    public void setLargeNum(int largeNum) {
        this.largeNum = largeNum;
    }

    public double getLargeOriginalValue() {
        return largeOriginalValue;
    }

    public void setLargeOriginalValue(double largeOriginalValue) {
        this.largeOriginalValue = largeOriginalValue;
    }

    public double getLargeNormalRate() {
        return largeNormalRate;
    }

    public void setLargeNormalRate(double largeNormalRate) {
        this.largeNormalRate = largeNormalRate;
    }

    public double getLargeFaultRate() {
        return largeFaultRate;
    }

    public void setLargeFaultRate(double largeFaultRate) {
        this.largeFaultRate = largeFaultRate;
    }
}
