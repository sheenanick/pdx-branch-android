package com.epicodus.pdxbranch.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.pdxbranch.R;
import com.epicodus.pdxbranch.models.Post;
import com.squareup.picasso.Picasso;

public class FirebasePostViewHolder extends RecyclerView.ViewHolder{
    private static final int MAX_WIDTH = 100;
    private static final int MAX_HEIGHT = 100;
    View mView;
    Context mContext;

    public FirebasePostViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindPost(Post post) {
        ImageView memberProfileImageView = (ImageView) mView.findViewById(R.id.profileImageView);
        TextView userNameTextView = (TextView) mView.findViewById(R.id.userNameTextView);
        TextView contentTextView = (TextView) mView.findViewById(R.id.contentTextView);

        userNameTextView.setText(post.getAuthor());
        contentTextView.setText(post.getContent());
        Picasso.with(mContext)
                .load(post.getAuthorImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(memberProfileImageView);
    }
}
