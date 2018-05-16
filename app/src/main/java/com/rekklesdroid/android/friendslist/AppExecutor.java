package com.rekklesdroid.android.friendslist;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutor {

    private static AppExecutor INSTANCE;
    private final Executor diskIO;

    private AppExecutor(Executor diskIO) {
        this.diskIO = diskIO;
    }

    public static AppExecutor getExecutor() {
        if (INSTANCE == null) {
            synchronized (AppExecutor.class) {
                INSTANCE = new AppExecutor(Executors.newSingleThreadExecutor());
            }
        }
        return INSTANCE;
    }

    public Executor getDiskIO() {
        return diskIO;
    }
}
