package com.rekklesdroid.android.friendslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.rekklesdroid.android.friendslist.model.RandomuserResult;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> {


    private List<RandomuserResult> randomuserResults;

    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imv_friend_photo)
        ImageView imv_photo;
        @BindView(R.id.txt_friend_name)
        TextView txt_name;

        String photoUrl;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {

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

    }

    private void putFriendPhoto(ViewHolder holder, RandomuserResult result) {
        try {
            holder.photoUrl = result.getPicture().getMedium();

            Picasso.with(context)
                    .load(holder.photoUrl)
                    .resize(72, 72)
                    .centerCrop()
                    .into(holder.imv_photo);
        } catch (NullPointerException ex) {
// TODO: 15.05.2018 add icon dont have image
        }
    }

    @Override
    public int getItemCount() {
        return randomuserResults.size();
    }
}
