package com.example.byblosapplication;

import java.io.Serializable;

public class Users implements Serializable {
    private String username;
    private String email;
    private String password;
    private String role;
    private String address;
    private String phone;
    private String city;
    private String country;
    private String province;
    private String ZIP;

    public Users(String username, String email, String password, String role, String address, String phone, String city, String country, String province, String ZIP) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.address = address;
        this.phone = phone;
        this.city = city;
        this.country = country;
        this.province = province;
        this.ZIP = ZIP;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZIP() {
        return ZIP;
    }

    public void setZIP(String ZIP) {
        this.ZIP = ZIP;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Users() {
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    //"Username: "+username+"\n"+
    public String toString(){

        return "Email: "+email+"\n"+"Password: "+password+"\n"+"Role: "+role+"\n";
    }
}
