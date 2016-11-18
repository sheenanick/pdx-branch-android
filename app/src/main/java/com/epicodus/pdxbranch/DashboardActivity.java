package com.epicodus.pdxbranch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity {
    @Bind(R.id.testTextView) TextView mTestTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String first_name = intent.getStringExtra("first_name");
        String last_name = intent.getStringExtra("last_name");
        String screen_name = intent.getStringExtra("screen_name");
        String city = intent.getStringExtra("city");
        String state = intent.getStringExtra("state");
        String zip_code = intent.getStringExtra("zip_code");
        String text = "first_name: " + first_name + ", last_name: " + last_name + ", screen_name: " + screen_name + ", city: " + city + ", state: " + state + ", zip_code: " + zip_code;
        mTestTextView.setText(text);
    }
}
