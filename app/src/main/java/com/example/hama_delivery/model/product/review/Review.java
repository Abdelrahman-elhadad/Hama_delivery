package com.example.hama_delivery.model.product.review;

import com.example.hama_delivery.model.user.User;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Review implements Serializable {
    @SerializedName("rate")
    private float rate;

    @SerializedName("review")
    private String review;

    @SerializedName("user")
    private User user;

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
