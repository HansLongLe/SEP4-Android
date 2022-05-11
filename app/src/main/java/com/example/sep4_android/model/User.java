package com.example.sep4_android.model;

public class User {
    String email, password, uid, role;

    public User(String email, String password, String uid) {
        this.email = email;
        this.password = password;
        this.uid = uid;
    }

    public User() {}

    public User(String email) {
        this.email = email;
        this.role = Roles.USER.name().toLowerCase();
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

    public String getUid() { return uid; }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
