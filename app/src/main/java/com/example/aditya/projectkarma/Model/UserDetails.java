package com.example.aditya.projectkarma.Model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserDetails implements Serializable {
    @SerializedName("user_name")
    private String userName = "";
    @SerializedName("user_paswword")
    private String userPassword = "";
    @SerializedName("user_email")
    private String userEmail = "";
    @SerializedName("user_mobile")
    private String userMobile = "";

    public UserDetails() {
    }

    public UserDetails(String name, String email, String pass, String mobile) {
        userName = name;
        userPassword = pass;
        userEmail = email;
        userMobile = mobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}