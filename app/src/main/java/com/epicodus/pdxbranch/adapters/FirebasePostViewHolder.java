package com.epicodus.pdxbranch.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.pdxbranch.R;
import com.epicodus.pdxbranch.models.Post;
import com.epicodus.pdxbranch.ui.DashboardActivity;
import com.epicodus.pdxbranch.ui.ProfileActivity;
import com.squareup.picasso.Picasso;

import java.io.IOException;

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

    public void bindPost(Post post) throws IOException {
        ImageView memberProfileImageView = (ImageView) mView.findViewById(R.id.profileImageView);
        mUserNameTextView = (TextView) mView.findViewById(R.id.userNameTextView);
        TextView contentTextView = (TextView) mView.findViewById(R.id.contentTextView);
        ImageView postImage = (ImageView) mView.findViewById(R.id.postImage);
        mPostAuthorId = post.getAuthorId();
        String postAuthorImage = post.getAuthorImageUrl();
        String postImageBitmap = post.getImage();

        if (postImageBitmap != null) {
            Bitmap imageBitmap = decodeFromFirebaseBase64(postImageBitmap);
            postImage.setImageBitmap(imageBitmap);
            postImage.setVisibility(View.VISIBLE);
        }

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

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }

    @Override
    public void onClick(View v) {
        if (v == mUserNameTextView) {
            Intent intent = new Intent(mContext, ProfileActivity.class);
            intent.putExtra("memberId", mPostAuthorId);
            mContext.startActivity(intent);
        }
    }
}
