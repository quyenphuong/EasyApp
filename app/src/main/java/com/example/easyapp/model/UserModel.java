package com.example.easyapp.model;

public class UserModel {

    private String address;
    private String email;
    private String name;
    private String phone;
    private String role;
    private String userid;
    private String username;


    public UserModel(){}

    public UserModel( String email, String name, String phone, String role) {

        this.email = email;
        this.name = name;
        this.phone = phone;
        this.role = role;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}