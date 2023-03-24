package com.example.myapplication.entity;

import java.io.Serializable;

public class Check implements Serializable {
    private int deptCode;

    public int getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(int deptCode) {
        this.deptCode = deptCode;
    }

    @Override
    public String toString() {
        return "Check{" +
                "deptCode=" + deptCode +
                '}';
    }
    public Check(int deptCode){
        this.deptCode = deptCode;
    }
}
