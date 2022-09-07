package com.phonepe.model;

import java.util.UUID;

public class User {
    private final String id;
    private final String email;
    private final String phone;

    public User(String id, String email, String phone) {
        this.id = id;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

}
