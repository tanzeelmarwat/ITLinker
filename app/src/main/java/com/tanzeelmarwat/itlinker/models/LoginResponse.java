package com.tanzeelmarwat.itlinker.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    int status;
    String message;
//    @SerializedName("Data")
    User user;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
