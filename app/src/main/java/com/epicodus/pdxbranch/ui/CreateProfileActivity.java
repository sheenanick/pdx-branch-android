package com.epicodus.pdxbranch.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.pdxbranch.R;
import com.epicodus.pdxbranch.models.Member;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateProfileActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = CreateProfileActivity.class.getSimpleName();
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.firstNameEditText) EditText mFirstNameEditText;
    @BindView(R.id.lastNameEditText) EditText mLastNameEditText;
    @BindView(R.id.screenNameEditText) EditText mScreenNameEditText;
    @BindView(R.id.zipCodeEditText) EditText mZipCodeEditText;
    @BindView(R.id.profileImageUrl) EditText mProfileImageUrl;
    @BindView(R.id.submitButton) Button mSubmitButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSubmitButton) {
            String firstName = mFirstNameEditText.getText().toString();
            String lastName = mLastNameEditText.getText().toString();
            String screenName = mScreenNameEditText.getText().toString();
            String zipCode = mZipCodeEditText.getText().toString();
            String profileImageUrl = mProfileImageUrl.getText().toString();

            if (firstName.equals("") || lastName.equals("") || screenName.equals("") || zipCode.equals("")) {
                if (firstName.equals("")) {
                    mFirstNameEditText.setError("Please enter first name");
                }
                if (lastName.equals("")) {
                    mLastNameEditText.setError("Please enter last name");
                }
                if (screenName.equals("")) {
                    mScreenNameEditText.setError("Please enter screen name");
                }
                if (zipCode.equals("")) {
                    mZipCodeEditText.setError("Please enter zip code");
                }
            } else {
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                Member member = new Member(firstName, lastName, screenName, zipCode, profileImageUrl, uid);
                DatabaseReference memberRef = FirebaseDatabase.getInstance().getReference("members").child(uid);
                memberRef.setValue(member);

                Intent intent = new Intent(CreateProfileActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }
    }
}
