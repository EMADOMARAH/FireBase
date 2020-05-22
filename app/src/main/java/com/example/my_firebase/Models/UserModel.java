package com.example.my_firebase.Models;

public class UserModel
{
   private String email,password,phone,username,userid;

    public UserModel() {
    }

    public UserModel(String email, String password, String phone, String username, String userid) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.username = username;
        this.userid = userid;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
