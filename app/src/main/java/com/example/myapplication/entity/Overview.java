package com.example.myapplication.entity;

import java.io.Serializable;

public class Overview implements Serializable {
    private int todoRepairNum;
    private int underRepairNum;
    private int todoCheckNum;

    public int getTodoRepairNum() {
        return todoRepairNum;
    }

    public void setTodoRepairNum(int todoRepairNum) {
        this.todoRepairNum = todoRepairNum;
    }

    public int getUnderRepairNum() {
        return underRepairNum;
    }

    public void setUnderRepairNum(int underRepairNum) {
        this.underRepairNum = underRepairNum;
    }

    public int getTodoCheckNum() {
        return todoCheckNum;
    }

    public void setTodoCheckNum(int todoCheckNum) {
        this.todoCheckNum = todoCheckNum;
    }

    @Override
    public String toString() {
        return "Overview{" +
                "todoRepairNum=" + todoRepairNum +
                ", underRepairNum=" + underRepairNum +
                ", todoCheckNum=" + todoCheckNum +
                '}';
    }
}
