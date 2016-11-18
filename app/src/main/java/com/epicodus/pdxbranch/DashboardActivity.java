package com.epicodus.pdxbranch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.greetingTextView) TextView mGreetingTextView;
    @Bind(R.id.addPostEditText) EditText mAddPostEditText;
    @Bind(R.id.postButton) Button mPostButton;
    @Bind(R.id.newsFeedListView) ListView mNewsFeedListView;
    ArrayList<String> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        mPostButton.setOnClickListener(this);

        Intent intent = getIntent();
        String first_name = intent.getStringExtra("first_name");
        String last_name = intent.getStringExtra("last_name");
        String screen_name = intent.getStringExtra("screen_name");
        String city = intent.getStringExtra("city");
        String state = intent.getStringExtra("state");
        String zip_code = intent.getStringExtra("zip_code");

        String greeting = "Hello, " + first_name;
        mGreetingTextView.setText(greeting);
    }

    @Override
    public void onClick(View v) {
        if (v == mPostButton) {
            posts.add(0, mAddPostEditText.getText().toString());
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, posts);
            mNewsFeedListView.setAdapter(adapter);
        }
    }
}
