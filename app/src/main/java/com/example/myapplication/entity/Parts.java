package com.example.myapplication.entity;

import java.io.Serializable;

public class Parts implements Serializable {
    private String parts;
    private String model;
    private String sn;
    private int usedNum;
    private String repairCode;

    public String getParts() {
        return parts;
    }

    public void setParts(String parts) {
        this.parts = parts;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(int usedNum) {
        this.usedNum = usedNum;
    }

    public String getRepairCode() {
        return repairCode;
    }

    public void setRepairCode(String repairCode) {
        this.repairCode = repairCode;
    }

    @Override
    public String toString() {
        return "Parts{" +
                "parts='" + parts + '\'' +
                ", model='" + model + '\'' +
                ", sn='" + sn + '\'' +
                ", usedNum=" + usedNum +
                ", repairCode='" + repairCode + '\'' +
                '}';
    }
}
