package com.example.aditya.projectkarma.Model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustomerDetails implements Serializable {
    @SerializedName("user_email")
    private String userEmail = "";
    @SerializedName("c_name")
    private String customerName = "";
    @SerializedName("c_age")
    private String customersAge = "";
    @SerializedName("c_dob")
    private String customersDob = "";
    @SerializedName("c_lat")
    private String custLat = "";
    @SerializedName("c_longitude")
    private String custLong = "";

    public CustomerDetails(String email, String name, String age, String dob, String lat, String lon) {
        userEmail = email;
        customerName = name;
        customersAge = age;
        customersDob = dob;
        custLat = lat;
        custLong = lon;
    }

    public CustomerDetails() {

    }

    public CustomerDetails(String email, String name, String age, String dob) {
        userEmail = email;
        customerName = name;
        customersAge = age;
        customersDob = dob;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public String getCustLat() {
        return custLat;
    }

    public String getCustLong() {
        return custLong;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomersAge() {
        return customersAge;
    }

    public String getCustomersDob() {
        return customersDob;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setCustLat(String custLat) {
        this.custLat = custLat;
    }

    public void setCustLong(String custLong) {
        this.custLong = custLong;
    }

    public void setCustomersAge(String customersAge) {
        this.customersAge = customersAge;
    }

    public void setCustomersDob(String customersDob) {
        this.customersDob = customersDob;
    }
}


