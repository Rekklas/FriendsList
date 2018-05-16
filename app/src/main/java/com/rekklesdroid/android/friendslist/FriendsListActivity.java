package com.rekklesdroid.android.friendslist;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rekklesdroid.android.friendslist.model.RandomuserJSON;
import com.rekklesdroid.android.friendslist.model.RandomuserResult;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

public class FriendsListActivity extends AppCompatActivity {

    @BindView(R.id.recview_friends_list)
    RecyclerView friendList;

    @BindView(R.id.swipe_refresh_friends_list)
    SwipeRefreshLayout swipeRefreshLayout;

    private LinearLayoutManager friendListManager;
    private RecyclerView.Adapter friendListAdapter;

    List<RandomuserResult> randomuserResults = new ArrayList<RandomuserResult>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_friends);
        ButterKnife.bind(this);

        friendList.setHasFixedSize(true);
        friendListManager = new LinearLayoutManager(this);
        friendList.setLayoutManager(friendListManager);

        GetFriendsAsyncTask getFriendsAsyncTask = new GetFriendsAsyncTask(this);
        getFriendsAsyncTask.execute();

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener(){

                    @Override
                    public void onRefresh() {
                        recreate();
                    }
                }
        );
    }

    private static class GetFriendsAsyncTask extends AsyncTask<Void, Void, List<RandomuserResult>> {

        private final WeakReference<FriendsListActivity> listActivityWeakReference;

        private GetFriendsAsyncTask(FriendsListActivity listActivity) {
            listActivityWeakReference = new WeakReference<>(listActivity);
        }

        @Override
        protected List<RandomuserResult> doInBackground(Void... voids) {

            RandomuserService randomuserService = RandomuserService.retrofit.create(RandomuserService.class);
            final Call<RandomuserJSON> call = randomuserService.getFriends();

            try {
                RandomuserJSON randomuserJSON = call.execute().body();
                listActivityWeakReference.get().randomuserResults = randomuserJSON.getResults();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return listActivityWeakReference.get().randomuserResults;
        }

        @Override
        protected void onPostExecute(List<RandomuserResult> randomuserResults) {
            super.onPostExecute(randomuserResults);
            listActivityWeakReference.get().friendListAdapter = new FriendsListAdapter(listActivityWeakReference.get().getApplicationContext(), randomuserResults);
            listActivityWeakReference.get().friendList.setAdapter(listActivityWeakReference.get().friendListAdapter);
        }
    }


}
