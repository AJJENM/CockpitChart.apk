package com.example.myapplication.entity;

import java.io.Serializable;

public class Info implements Serializable{

    private String deptId;
    private String dept;
    private int repairNum;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getRepairNum() {
        return repairNum;
    }

    public void setRepairNum(int repairNum) {
        this.repairNum = repairNum;
    }

    @Override
    public String toString() {
        return "Info{" +
                "deptId='" + deptId + '\'' +
                ", dept='" + dept + '\'' +
                ", repairNum=" + repairNum +
                '}';
    }
}
