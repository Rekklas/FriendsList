package com.rekklesdroid.android.friendslist;

import com.rekklesdroid.android.friendslist.model.RandomuserJSON;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface RandomuserService {

    @GET("?page=1&results=10&inc=name,email,registered,picture")
    Call<RandomuserJSON> getFriends();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
