package com.rekklesdroid.android.friendslist.model;

import android.arch.persistence.room.ColumnInfo;

public class RandomuserPicture {

    @ColumnInfo(name = "photo_url")
    private String large;

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
