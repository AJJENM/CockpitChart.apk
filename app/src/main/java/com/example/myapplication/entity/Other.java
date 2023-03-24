package com.example.myapplication.entity;

import java.io.Serializable;

public class Other implements Serializable {
    private int supplierNum;
    private int producerNum;
    private int maintainerNum;
    private int deptNum;
    private int userNum;

    public int getSupplierNum() {
        return supplierNum;
    }

    public void setSupplierNum(int supplierNum) {
        this.supplierNum = supplierNum;
    }

    public int getProducerNum() {
        return producerNum;
    }

    public void setProducerNum(int producerNum) {
        this.producerNum = producerNum;
    }

    public int getMaintainerNum() {
        return maintainerNum;
    }

    public void setMaintainerNum(int maintainerNum) {
        this.maintainerNum = maintainerNum;
    }

    public int getDeptNum() {
        return deptNum;
    }

    public void setDeptNum(int deptNum) {
        this.deptNum = deptNum;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    @Override
    public String toString() {
        return "Other{" +
                "supplierNum=" + supplierNum +
                ", producerNum=" + producerNum +
                ", maintainerNum=" + maintainerNum +
                ", deptNum=" + deptNum +
                ", userNum=" + userNum +
                '}';
    }
}
