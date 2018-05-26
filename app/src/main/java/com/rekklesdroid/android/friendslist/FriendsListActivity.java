package com.rekklesdroid.android.friendslist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.rekklesdroid.android.friendslist.adapter.FriendsListAdapter;
import com.rekklesdroid.android.friendslist.database.AppDatabase;
import com.rekklesdroid.android.friendslist.model.RandomuserJSON;
import com.rekklesdroid.android.friendslist.model.RandomuserResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FriendsListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recview_friends_list)
    RecyclerView friendList;

    @BindView(R.id.swipe_refresh_friends_list)
    SwipeRefreshLayout swipeRefreshLayout;

    private LinearLayoutManager friendListManager;
    private RecyclerView.Adapter friendListAdapter;

    List<RandomuserResult> randomuserResults = new ArrayList<RandomuserResult>();

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_friends);
        ButterKnife.bind(this);

        friendList.setHasFixedSize(true);
        friendListManager = new LinearLayoutManager(this);
        friendList.setLayoutManager(friendListManager);

        swipeRefreshLayout.setOnRefreshListener(this);

        db = AppDatabase.getDatabase(getApplicationContext());

        loadData();
    }

    @Override
    public void onRefresh() {
        recreate();
    }

    /**
     * When activity is onPause it deletes all results from database and
     * cache new downloaded results instead
     */
    @Override
    protected void onPause() {
        super.onPause();
        AppExecutor.getExecutor().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                db.friendDao().deleteResults();
                db.friendDao().insertResults(randomuserResults);
            }
        });
    }

    /**
     * This method is used for loading data with retrofit
     */
    private void loadData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RandomuserService randomuserService = retrofit.create(RandomuserService.class);
        Call<RandomuserJSON> call = randomuserService.getFriends();
        call.enqueue(new Callback<RandomuserJSON>() {
            @Override
            public void onResponse(Call<RandomuserJSON> call, Response<RandomuserJSON> response) {
                randomuserResults = response.body().getResults();

                sortResults(randomuserResults);
                populateRecViewWithData();
            }

            @Override
            public void onFailure(Call<RandomuserJSON> call, Throwable t) {
                setupViewModel();
                Toast.makeText(FriendsListActivity.this, "An error occurred during networking.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Method loads cached data
     */
    private void setupViewModel() {
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getResults().observe(this, new Observer<List<RandomuserResult>>() {
            @Override
            public void onChanged(@Nullable List<RandomuserResult> results) {
                randomuserResults = results;
                populateRecViewWithData();
            }
        });
    }

    /**
     * Method sort all friends by their first name
     *
     * @param randomuserResults list of friends getting from randomuser API
     */
    private void sortResults(List<RandomuserResult> randomuserResults) {
        Collections.sort(randomuserResults, new Comparator<RandomuserResult>() {
            @Override
            public int compare(RandomuserResult o1, RandomuserResult o2) {

                return o1.getName().getFirst().compareTo(o2.getName().getFirst());
            }
        });
    }

    private void populateRecViewWithData() {
        friendListAdapter = new FriendsListAdapter(getApplicationContext(), randomuserResults);
        friendList.setAdapter(friendListAdapter);
    }
}
