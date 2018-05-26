package com.rekklesdroid.android.friendslist.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.rekklesdroid.android.friendslist.model.RandomuserResult;

/**
 * Read for possible singleton implementation here
 * Check the "Initialization-on-demand holder idiom"
 * @link https://javarevealed.wordpress.com/tag/initialization-on-demand-holder/
 */
@Database(entities = {RandomuserResult.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FriendDao friendDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "word_database")
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}
