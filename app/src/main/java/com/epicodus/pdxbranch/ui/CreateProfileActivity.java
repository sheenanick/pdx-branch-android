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

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateProfileActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.firstNameEditText) EditText mFirstNameEditText;
    @Bind(R.id.lastNameEditText) EditText mLastNameEditText;
    @Bind(R.id.screenNameEditText) EditText mScreenNameEditText;
    @Bind(R.id.cityEditText) EditText mCityEditText;
    @Bind(R.id.stateEditText) EditText mStateEditText;
    @Bind(R.id.zipCodeEditText) EditText mZipCodeEditText;
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
            String first_name = mFirstNameEditText.getText().toString();
            String last_name = mLastNameEditText.getText().toString();
            String screen_name = mScreenNameEditText.getText().toString();
            String city = mCityEditText.getText().toString();
            String state = mStateEditText.getText().toString();
            String zip_code =mZipCodeEditText.getText().toString();

            if (first_name.equals("") || last_name.equals("") || screen_name.equals("") || city.equals("") || state.equals("") || zip_code.equals("")) {
                if (first_name.equals("")) {
                    mFirstNameEditText.setHintTextColor(ContextCompat.getColor(CreateProfileActivity.this, R.color.colorAccent));
                }
                if (last_name.equals("")) {
                    mLastNameEditText.setHintTextColor(ContextCompat.getColor(CreateProfileActivity.this, R.color.colorAccent));
                }
                if (screen_name.equals("")) {
                    mScreenNameEditText.setHintTextColor(ContextCompat.getColor(CreateProfileActivity.this, R.color.colorAccent));
                }
                if (city.equals("")) {
                    mCityEditText.setHintTextColor(ContextCompat.getColor(CreateProfileActivity.this, R.color.colorAccent));
                }
                if (state.equals("")) {
                    mStateEditText.setHintTextColor(ContextCompat.getColor(CreateProfileActivity.this, R.color.colorAccent));
                }
                if (zip_code.equals("")) {
                    mZipCodeEditText.setHintTextColor(ContextCompat.getColor(CreateProfileActivity.this, R.color.colorAccent));
                }
                Toast.makeText(CreateProfileActivity.this, "Please fill out entire form", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(CreateProfileActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }
    }
}
