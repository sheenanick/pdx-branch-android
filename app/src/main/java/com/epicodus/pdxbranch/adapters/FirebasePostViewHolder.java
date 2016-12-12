package com.epicodus.pdxbranch.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.pdxbranch.R;
import com.epicodus.pdxbranch.models.Post;

public class FirebasePostViewHolder extends RecyclerView.ViewHolder{
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
    }


}
