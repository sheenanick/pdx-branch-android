package com.epicodus.pdxbranch.ui;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.pdxbranch.R;
import com.epicodus.pdxbranch.models.Member;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateProfileActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.firstNameEditText) EditText mFirstNameEditText;
    @Bind(R.id.lastNameEditText) EditText mLastNameEditText;
    @Bind(R.id.screenNameEditText) EditText mScreenNameEditText;
    @Bind(R.id.zipCodeEditText) EditText mZipCodeEditText;
    @Bind(R.id.profileImageUrl) EditText mProfileImageUrl;
    @Bind(R.id.submitButton) Button mSubmitButton;

    @Override
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
            String zipCode =mZipCodeEditText.getText().toString();
            String profileImageUrl =mProfileImageUrl.getText().toString();

            if (firstName.equals("") || lastName.equals("") || screenName.equals("") || zipCode.equals("")) {
                if (firstName.equals("")) {
                    mFirstNameEditText.setHintTextColor(ContextCompat.getColor(CreateProfileActivity.this, R.color.colorAccent));
                }
                if (lastName.equals("")) {
                    mLastNameEditText.setHintTextColor(ContextCompat.getColor(CreateProfileActivity.this, R.color.colorAccent));
                }
                if (screenName.equals("")) {
                    mScreenNameEditText.setHintTextColor(ContextCompat.getColor(CreateProfileActivity.this, R.color.colorAccent));
                }
                if (zipCode.equals("")) {
                    mZipCodeEditText.setHintTextColor(ContextCompat.getColor(CreateProfileActivity.this, R.color.colorAccent));
                }
                Toast.makeText(CreateProfileActivity.this, "Please fill out entire form", Toast.LENGTH_LONG).show();
            } else {
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                Member member = new Member(firstName, lastName, screenName, zipCode, profileImageUrl);
                DatabaseReference memberRef = FirebaseDatabase.getInstance().getReference("members").child(uid);
                memberRef.push().setValue(member);

                Intent intent = new Intent(CreateProfileActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }
    }
}
