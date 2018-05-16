package com.rekklesdroid.android.friendslist.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rekklesdroid.android.friendslist.DetailFriendActivity;
import com.rekklesdroid.android.friendslist.R;
import com.rekklesdroid.android.friendslist.model.RandomuserResult;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> {

    /**
     * Constants key values for data transferring via intent
     */
    public static final String EXTRA_FRIEND_FIRSTNAME = "firstname";
    public static final String EXTRA_FRIEND_LASTNAME = "lastname";
    public static final String EXTRA_FRIEND_EMAIL = "email";
    public static final String EXTRA_FRIEND_REGISTERED = "registered";
    public static final String EXTRA_FRIEND_PHOTO_URL = "photo";

    private List<RandomuserResult> randomuserResults;

    /**
     * The application context for getting resources
     */
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imv_friend_photo)
        ImageView imv_photo;
        @BindView(R.id.txt_friend_name)
        TextView txt_name;

        private String lastName;
        private String registered;
        private String email;
        private String photoUrl;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {

            Context context = txt_name.getContext();
            Intent detailIntent = new Intent(context, DetailFriendActivity.class);
            detailIntent.putExtra(EXTRA_FRIEND_FIRSTNAME, txt_name.getText());
            detailIntent.putExtra(EXTRA_FRIEND_LASTNAME, lastName);
            detailIntent.putExtra(EXTRA_FRIEND_EMAIL, email);
            detailIntent.putExtra(EXTRA_FRIEND_REGISTERED, registered);
            detailIntent.putExtra(EXTRA_FRIEND_PHOTO_URL, photoUrl);
            context.startActivity(detailIntent);
        }
    }

    public FriendsListAdapter(Context context, List<RandomuserResult> randomuserResults) {
        this.context = context;
        this.randomuserResults = randomuserResults;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RandomuserResult result = randomuserResults.get(position);

        holder.txt_name.setText(result.getName().getFirst());
        putFriendPhoto(holder, result);

        holder.lastName = randomuserResults.get(position).getName().getLast();
        holder.email = randomuserResults.get(position).getEmail();
        holder.registered = randomuserResults.get(position).getRegistered();
        holder.photoUrl = randomuserResults.get(position).getPicture().getLarge();

    }

    /**
     * This method get url of photo and download it by Picasso
     *
     * @param holder holder which is responsible for displaying particular friend
     * @param result result which is particular friend
     */
    private void putFriendPhoto(ViewHolder holder, RandomuserResult result) {
        try {
            holder.photoUrl = result.getPicture().getLarge();

            Picasso.with(context)
                    .load(holder.photoUrl)
                    .resize(72, 72)
                    .centerCrop()
                    .into(holder.imv_photo);
        } catch (NullPointerException ex) {
            holder.imv_photo.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_dont_have_photo));
        }
    }

    @Override
    public int getItemCount() {
        return randomuserResults.size();
    }
}
