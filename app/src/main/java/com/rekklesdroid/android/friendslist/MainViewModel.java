package com.rekklesdroid.android.friendslist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.rekklesdroid.android.friendslist.database.AppDatabase;
import com.rekklesdroid.android.friendslist.model.RandomuserResult;

import java.util.List;

/**
 * This viewModel class is using to cache list of friends wrapped in livedata object
 */
public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<RandomuserResult>> results;

    public MainViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getDatabase(this.getApplication());
        Log.d(TAG, "MainViewModel: retrieving list of friends from database");
        results = database.friendDao().getAllCachedResults();
    }

    public LiveData<List<RandomuserResult>> getResults() {
        return results;
    }
}
