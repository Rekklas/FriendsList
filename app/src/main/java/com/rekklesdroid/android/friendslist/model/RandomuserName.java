package com.rekklesdroid.android.friendslist.model;

import android.arch.persistence.room.ColumnInfo;

public class RandomuserName {

    @ColumnInfo(name = "first_name")
    private String first;

    @ColumnInfo(name = "last_name")
    private String last;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
