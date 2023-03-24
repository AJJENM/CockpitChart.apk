package com.example.myapplication.entity;

import java.io.Serializable;

public class Result implements Serializable {
    private boolean success;
    private String msg;
    private Object response;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", response='" + response + '\'' +
                '}';
    }

    public void setWarn(String msg, Object result){
        this.msg = "warning-" + msg;
        this.response = result;
    }
    public void setErr(){
        this.msg = "error-坏掉了";
        this.response = null;
    }
//    private int code;
//    private String msg;
//    private Object data;
//
//    public int getCode() {
//        return code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public Object getData() {
//        return data;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public void setData(Object data) {
//        this.data = data;
//    }
//
//    @Override
//    public String toString() {
//        return "Result{" +
//                "code=" + code +
//                ", msg='" + msg + '\'' +
//                ", data='" + data + '\'' +
//                '}';
//    }
//
//    public void setSuccess(String msg, String data) {
//        this.code = 0;
//        this.msg = "success-" + msg;
//        this.data = data;
//    }
//
//    public void setInfo(String msg, String data) {
//        this.code = 1;
//        this.msg = "warning-" + msg;
//        this.data = data;
//    }
//    public void setWarn(String msg, Object result){
//        this.code=400;
//        this.msg="warning-"+msg;
//        this.data=result;
//    }
//    public void setErr(){
//        this.code=500;
//        this.msg="error-服务器在忙，请稍后再试一试吧！";
//        this.data=null;
//    }
}
