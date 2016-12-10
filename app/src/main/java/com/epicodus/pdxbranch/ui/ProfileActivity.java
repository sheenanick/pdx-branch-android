package com.epicodus.pdxbranch.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.pdxbranch.R;
import com.epicodus.pdxbranch.models.Member;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.profileImageView) ImageView mProfileImageView;
    @Bind(R.id.memberNameTextView) TextView mMemberNameTextView;
    @Bind(R.id.screenNameTextView) TextView mScreenNameTextView;
    @Bind(R.id.zipCodeTextView) TextView mZipCodeTextView;

    private DatabaseReference mCurrentMemberReference;
    private ValueEventListener mCurrentMemberReferenceListener;

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
                Member currentUser = dataSnapshot.getValue(Member.class);
                String firstName = currentUser.getFirstName();
                String lastName = currentUser.getLastName();
                String name = firstName + " " + lastName;
                String imageUrl = currentUser.getProfileImageUrl();
                mMemberNameTextView.setText(name);
                mScreenNameTextView.setText("(" + currentUser.getScreenName() + ")");
                mZipCodeTextView.setText(currentUser.getZipCode());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
