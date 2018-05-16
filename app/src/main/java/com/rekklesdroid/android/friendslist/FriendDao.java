package com.rekklesdroid.android.friendslist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.rekklesdroid.android.friendslist.model.RandomuserResult;

import java.util.List;

import retrofit2.http.DELETE;

@Dao
public interface FriendDao {

    @Query("SELECT * FROM friend ORDER BY first_name")
    List<RandomuserResult> getAllCachedResults();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertResults(List<RandomuserResult> results);

    @Query("DELETE FROM friend")
    void deleteResults();

}
