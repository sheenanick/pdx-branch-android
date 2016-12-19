package com.epicodus.pdxbranch.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.pdxbranch.R;
import com.epicodus.pdxbranch.adapters.FirebasePostViewHolder;
import com.epicodus.pdxbranch.models.Post;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostsListFragment extends Fragment {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private DatabaseReference mPostReference;

    public PostsListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts_list, container, false);
        ButterKnife.bind(this, view);
        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mPostReference = FirebaseDatabase.getInstance().getReference("members").child(currentUserId).child("posts");
        setUpFirebaseAdapter();
        return view;
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Post, FirebasePostViewHolder>
                (Post.class, R.layout.post_list_item, FirebasePostViewHolder.class,
                        mPostReference) {

            @Override
            protected void populateViewHolder(FirebasePostViewHolder viewHolder,
                                              Post model, int position) {
                try {
                    viewHolder.bindPost(model);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

}
