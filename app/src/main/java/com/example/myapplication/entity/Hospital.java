package com.example.myapplication.entity;

import java.io.Serializable;

public class Hospital implements Serializable {
    private String deptId;
    private String deptCode;
    private String deptName;
    private String director;
    private int assetQuantity;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAssetQuantity() {
        return assetQuantity;
    }

    public void setAssetQuantity(int assetQuantity) {
        this.assetQuantity = assetQuantity;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "deptId='" + deptId + '\'' +
                ", deptCode='" + deptCode + '\'' +
                ", deptName='" + deptName + '\'' +
                ", director='" + director + '\'' +
                ", assetQuantity=" + assetQuantity +
                '}';
    }
}
