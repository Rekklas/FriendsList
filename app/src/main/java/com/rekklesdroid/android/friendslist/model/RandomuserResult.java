package com.rekklesdroid.android.friendslist.model;

public class RandomuserResult {

    private RandomuserName name;

    private RandomuserPicture picture;

    private String email;

    private String registered;

    public RandomuserName getName() {
        return name;
    }

    public void setName(RandomuserName name) {
        this.name = name;
    }

    public RandomuserPicture getPicture() {
        return picture;
    }

    public void setPicture(RandomuserPicture picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }
}
