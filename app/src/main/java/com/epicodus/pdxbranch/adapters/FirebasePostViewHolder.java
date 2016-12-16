package com.epicodus.pdxbranch.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.pdxbranch.R;
import com.epicodus.pdxbranch.models.Post;
import com.epicodus.pdxbranch.ui.DashboardActivity;
import com.epicodus.pdxbranch.ui.ProfileActivity;
import com.squareup.picasso.Picasso;

public class FirebasePostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 100;
    private static final int MAX_HEIGHT = 100;
    private TextView mUserNameTextView;
    private String mPostAuthorId;
    private View mView;
    private Context mContext;

    public FirebasePostViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindPost(Post post) {
        ImageView memberProfileImageView = (ImageView) mView.findViewById(R.id.profileImageView);
        mUserNameTextView = (TextView) mView.findViewById(R.id.userNameTextView);
        TextView contentTextView = (TextView) mView.findViewById(R.id.contentTextView);
        mPostAuthorId = post.getAuthorId();
        String postAuthorImage = post.getAuthorImageUrl();

        mUserNameTextView.setText(post.getAuthor());
        contentTextView.setText(post.getContent());
        if (!postAuthorImage.equals("")) {
            Picasso.with(mContext)
                    .load(postAuthorImage)
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(memberProfileImageView);
        }
        mUserNameTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mUserNameTextView) {
            Intent intent = new Intent(mContext, ProfileActivity.class);
            intent.putExtra("authorId", mPostAuthorId);
            mContext.startActivity(intent);
        }
    }
}
