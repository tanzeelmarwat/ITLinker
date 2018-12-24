package com.tanzeelmarwat.itlinker.models;

import com.google.gson.annotations.SerializedName;

public class Feed {
    String id;
    String type;
    String text;
    @SerializedName("feed_image")
    String feedImage;
    @SerializedName("user_name")
    String userName;
    @SerializedName("user_image")
    String userImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFeedImage() {
        return feedImage;
    }

    public void setFeedImage(String feedImage) {
        this.feedImage = feedImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
