package com.example.hama_delivery.model.user;

import com.example.hama_delivery.model.country.City;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UserLogin implements Serializable {

    @SerializedName("token")
    private String token;

    @SerializedName("delegate")
    private User delegate;


    public UserLogin() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getDelegate() {
        return delegate;
    }

    public void setDelegate(User delegate) {
        this.delegate = delegate;
    }
}
