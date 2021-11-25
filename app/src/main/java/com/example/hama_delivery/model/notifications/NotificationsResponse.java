package com.example.hama_delivery.model.notifications;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class NotificationsResponse {

    @SerializedName("status")
    private boolean status;

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<Notifications> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        if (code == null)
            code = "";
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Notifications> getData() {
        return data;
    }

    public void setData(List<Notifications> data) {
        this.data = data;
    }
}
