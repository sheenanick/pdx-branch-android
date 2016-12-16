package com.epicodus.pdxbranch.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.pdxbranch.R;
import com.epicodus.pdxbranch.models.Member;
import com.epicodus.pdxbranch.ui.ProfileActivity;
import com.squareup.picasso.Picasso;


public class FirebaseMemberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 100;
    private static final int MAX_HEIGHT = 100;
    private View mView;
    private Context mContext;
    private TextView mNameTextView;
    private String mMemberId;

    public FirebaseMemberViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindMember(Member member) {
        mNameTextView = (TextView) mView.findViewById(R.id.memberNameTextView);
        ImageView profileImage = (ImageView) mView.findViewById(R.id.profileImageView);

        String fullName = member.getFirstName() + " " + member.getLastName();
        String imageUrl = member.getProfileImageUrl();
        mMemberId = member.getPushId();

        mNameTextView.setText(fullName);
        if (!imageUrl.equals("")) {
            Picasso.with(mContext)
                    .load(imageUrl)
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(profileImage);
        }
        mNameTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mNameTextView) {
            Intent intent = new Intent(mContext, ProfileActivity.class);
            intent.putExtra("memberId", mMemberId);
            mContext.startActivity(intent);
        }
    }
}
