package com.appdev.group3.ecollect.data.model;

public class User {
    public String userId, name, email, mobile;

    public User() {
        // Default constructor (needed for Firebase)
    }

    public User(String userId, String name, String email, String mobile) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }
}