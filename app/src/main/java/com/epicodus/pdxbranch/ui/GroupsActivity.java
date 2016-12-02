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

import com.epicodus.pdxbranch.R;
import com.epicodus.pdxbranch.adapters.MeetupGroupAdapter;
import com.epicodus.pdxbranch.models.MeetupGroup;
import com.epicodus.pdxbranch.services.MeetupService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GroupsActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private MeetupGroupAdapter mAdapter;
    public ArrayList<MeetupGroup> mMeetupGroups = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getRecommendedGroups();
    }

    private void getRecommendedGroups() {
        final MeetupService meetupService = new MeetupService();
        meetupService.findRecommendedGroups(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mMeetupGroups = meetupService.processResults(response);

                GroupsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() { mAdapter = new MeetupGroupAdapter(getApplicationContext(), mMeetupGroups);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GroupsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                    }
                });

            }
        });
    }

}
