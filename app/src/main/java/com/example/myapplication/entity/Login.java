package com.example.myapplication.entity;

import java.io.Serializable;

public class Login implements Serializable {

    private String userName;
    private String password;
    private String mobilePhone;
    private String captcha;
    private String captchaKey;
    private String type;
    private String code;
    private String deptId;

    public Login(String username, String password, String deptId, String type) {
        this.userName = username;
        this.password = password;
        this.deptId = deptId;
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    public void setCaptchaKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Login{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", captcha='" + captcha + '\'' +
                ", captchaKey='" + captchaKey + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", deptId='" + deptId + '\'' +
                '}';
    }
}
