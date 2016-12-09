package com.epicodus.pdxbranch.ui;

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
import android.widget.Toast;

import com.epicodus.pdxbranch.R;
import com.google.firebase.auth.FirebaseAuth;

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
    String first_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mPostButton.setOnClickListener(this);

        Intent intent = getIntent();
        first_name = intent.getStringExtra("first_name");
        String greeting;
        if (first_name == null) {
            greeting = "Welcome to pdxBranch";
        } else {
            greeting = "Welcome to pdxBranch, " + first_name;
        }
        mGreetingTextView.setText(greeting);
    }

    @Override
    public void onClick(View v) {
        if (v == mPostButton) {
            String post = mAddPostEditText.getText().toString();
            if (post.equals("")) {
                Toast.makeText(DashboardActivity.this, "Enter something to post!", Toast.LENGTH_SHORT).show();
            } else {
                posts.add(0, post);
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, posts);
                mNewsFeedListView.setAdapter(adapter);
                mAddPostEditText.setText("");
            }

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
                logout();
                return true;

            case R.id.action_groups:
                Intent groupsIntent = new Intent(DashboardActivity.this, GroupsActivity.class);
                startActivity(groupsIntent);
                return true;

            case R.id.action_profile:
                Intent profileIntent = new Intent(DashboardActivity.this, ProfileActivity.class);
                profileIntent.putExtra("first_name", first_name);
                startActivity(profileIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
