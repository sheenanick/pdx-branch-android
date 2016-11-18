package com.epicodus.pdxbranch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
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
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        mSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSubmitButton) {
            Intent intent = new Intent(SignUpActivity.this, DashboardActivity.class);
            intent.putExtra("first_name", mFirstNameEditText.getText().toString());
            intent.putExtra("last_name", mLastNameEditText.getText().toString());
            intent.putExtra("screen_name", mScreenNameEditText.getText().toString());
            intent.putExtra("city", mCityEditText.getText().toString());
            intent.putExtra("state", mStateEditText.getText().toString());
            intent.putExtra("zip_code", mZipCodeEditText.getText().toString());
            startActivity(intent);
        }
    }
}
