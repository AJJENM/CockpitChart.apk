package com.example.myapplication.entity;

import java.io.Serializable;

public class History implements Serializable{
    private int year;
    private int repairNum;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRepairNum() {
        return repairNum;
    }

    public void setRepairNum(int repairNum) {
        this.repairNum = repairNum;
    }

    @Override
    public String toString() {
        return "History{" +
                "year=" + year +
                ", repairNum=" + repairNum +
                '}';
    }
}
