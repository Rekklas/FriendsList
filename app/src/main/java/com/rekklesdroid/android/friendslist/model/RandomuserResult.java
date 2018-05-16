package com.rekklesdroid.android.friendslist.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "friend")
public class RandomuserResult {

    @Embedded
    private RandomuserName name;

    @Embedded
    private RandomuserPicture picture;

    @PrimaryKey
    private @NonNull String email;

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
