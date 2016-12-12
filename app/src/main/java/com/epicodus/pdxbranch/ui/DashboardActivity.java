package com.epicodus.pdxbranch.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.pdxbranch.R;
import com.epicodus.pdxbranch.adapters.FirebasePostViewHolder;
import com.epicodus.pdxbranch.models.Post;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.greetingTextView) TextView mGreetingTextView;
    @Bind(R.id.addPostEditText) EditText mAddPostEditText;
    @Bind(R.id.postButton) Button mPostButton;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private String mFirstName;
    private String mLastName;
    private String mUserImageUrl;
    private DatabaseReference mCurrentMemberReference;
    private DatabaseReference mPostReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mPostButton.setOnClickListener(this);

        final String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mCurrentMemberReference = FirebaseDatabase.getInstance().getReference("members").child(currentUserId);
        mCurrentMemberReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                mFirstName = (String) map.get("firstName");
                mLastName = (String) map.get("lastName");
                mUserImageUrl = (String) map.get("profileImageUrl");
                String greeting = "Welcome to pdxBranch, " + mFirstName;
                mGreetingTextView.setText(greeting);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mPostReference = mCurrentMemberReference.child("posts");
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Post, FirebasePostViewHolder>
                (Post.class, R.layout.post_list_item, FirebasePostViewHolder.class,
                        mPostReference) {

            @Override
            protected void populateViewHolder(FirebasePostViewHolder viewHolder,
                                              Post model, int position) {
                viewHolder.bindPost(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onClick(View v) {
        if (v == mPostButton) {
            String content = mAddPostEditText.getText().toString().trim();

            if (content.equals("")) {
                Toast.makeText(DashboardActivity.this, "Enter something to post!", Toast.LENGTH_SHORT).show();
            } else {
                DatabaseReference pushRef = mCurrentMemberReference.child("posts").push();
                String pushId = pushRef.getKey();
                String author = mFirstName + " " + mLastName;
                Post post = new Post(author, mUserImageUrl, content, pushId);
                pushRef.setValue(post);
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
            case R.id.action_log_out:
                logout();
                return true;

            case R.id.action_groups:
                Intent groupsIntent = new Intent(DashboardActivity.this, GroupsActivity.class);
                startActivity(groupsIntent);
                return true;

            case R.id.action_profile:
                Intent profileIntent = new Intent(DashboardActivity.this, ProfileActivity.class);
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
