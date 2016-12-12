package com.epicodus.pdxbranch.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.pdxbranch.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.profileImageView) ImageView mProfileImageView;
    @Bind(R.id.memberNameTextView) TextView mMemberNameTextView;
    @Bind(R.id.screenNameTextView) TextView mScreenNameTextView;
    @Bind(R.id.zipCodeTextView) TextView mZipCodeTextView;

    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    private Context mContext = this;
    private DatabaseReference mCurrentMemberReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        final String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mCurrentMemberReference = FirebaseDatabase.getInstance().getReference("members").child(currentUserId);
        mCurrentMemberReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                String firstName = (String) map.get("firstName");
                String lastName = (String) map.get("lastName");
                String name = firstName + " " + lastName;
                String screenName = (String) map.get("screenName");
                String imageUrl = (String) map.get("profileImageUrl");
                String zipCode = (String) map.get("zipCode");
                mMemberNameTextView.setText(name);
                mScreenNameTextView.setText("(" + screenName + ")");
                mZipCodeTextView.setText(zipCode);
                Picasso.with(mContext)
                        .load(imageUrl)
                        .resize(MAX_WIDTH, MAX_HEIGHT)
                        .centerCrop()
                        .into(mProfileImageView);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
