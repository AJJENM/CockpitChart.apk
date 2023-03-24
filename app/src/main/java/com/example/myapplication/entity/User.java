package com.example.myapplication.entity;

import java.io.Serializable;

public class User implements Serializable {
    private int userId;
    private String username;
    private String password;
    private String mobile;
    //change pwd
    public User(String mobile, String password, int userId, String username) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
    }

    public User(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public User() {

    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getMobile() {
        return mobile;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "User{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
