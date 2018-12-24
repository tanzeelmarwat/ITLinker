package com.tanzeelmarwat.itlinker.models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class User {
    String id;
    @SerializedName("user_type")
    String userType;
    String name;
    String email;
    String contact;

    private static User currentUser;
    private static HashMap<Integer, Object> userTypes;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
    }

    public static HashMap<Integer, Object> getUserTypes() {
        return userTypes;
    }

    public static void setUserTypes(HashMap<Integer, Object> userTypes) {
        User.userTypes = userTypes;
    }
}
