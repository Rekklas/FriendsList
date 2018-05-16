package com.rekklesdroid.android.friendslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.rekklesdroid.android.friendslist.adapter.FriendsListAdapter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFriendActivity extends AppCompatActivity {

    @BindView(R.id.imv_friend_photo)
    ImageView imv_friend_photo;
    @BindView(R.id.txt_friend_name)
    TextView txt_friend_name;
    @BindView(R.id.txt_friend_email)
    TextView txt_friend_email;
    @BindView(R.id.txt_friend_registered)
    TextView getTxt_friend_registered;

    private String firstName;
    private String lastName;
    private String email;
    private String registered;
    private String photoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_friend);
        ButterKnife.bind(this);

        retrieveIntentData();

        txt_friend_name.setText(firstName + " " + lastName);
        txt_friend_email.setText(email);
        getTxt_friend_registered.setText(registered);

        if(photoUrl != null){
            Picasso.with(getApplicationContext())
                    .load(photoUrl)
                    .resize(250,250)
                    .into(imv_friend_photo);
        } else {
            imv_friend_photo.setImageDrawable(getResources().getDrawable(R.drawable.ic_dont_have_photo));
        }
    }

    private void retrieveIntentData() {

        Bundle friend = getIntent().getExtras();
        firstName = friend.getString(FriendsListAdapter.EXTRA_FRIEND_FIRSTNAME);
        lastName = friend.getString(FriendsListAdapter.EXTRA_FRIEND_LASTNAME);
        email = friend.getString(FriendsListAdapter.EXTRA_FRIEND_EMAIL);
        registered = friend.getString(FriendsListAdapter.EXTRA_FRIEND_REGISTERED);
        photoUrl = friend.getString(FriendsListAdapter.EXTRA_FRIEND_PHOTO_URL);
    }
}
