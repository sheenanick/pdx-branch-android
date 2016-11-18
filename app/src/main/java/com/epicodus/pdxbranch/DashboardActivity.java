package com.epicodus.pdxbranch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    @Bind(R.id.toolbar) Toolbar mToolbar;
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

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mPostButton.setOnClickListener(this);

        Intent intent = getIntent();
        String first_name = intent.getStringExtra("first_name");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sign_out:
                // User chose the "Sign Out" item... until Sign in/out functionality is implemented simply reroute to MainActivity
                Intent signOutIntent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(signOutIntent);
                return true;

            case R.id.action_profile:
                Intent profileIntent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
