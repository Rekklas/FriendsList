package com.rekklesdroid.android.friendslist.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.rekklesdroid.android.friendslist.model.RandomuserResult;

import java.util.List;

@Dao
public interface FriendDao {

    @Query("SELECT * FROM friend ORDER BY first_name")
    List<RandomuserResult> getAllCachedResults();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertResults(List<RandomuserResult> results);

    @Query("DELETE FROM friend")
    void deleteResults();
}
