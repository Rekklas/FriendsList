package com.rekklesdroid.android.friendslist;

import com.rekklesdroid.android.friendslist.model.RandomuserJSON;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface RandomuserService {

    @GET("?results=10")
    Call<RandomuserJSON> getFriends();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
