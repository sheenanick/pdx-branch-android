package com.epicodus.pdxbranch.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.epicodus.pdxbranch.R;
import com.epicodus.pdxbranch.services.MeetupService;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GroupsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

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
                try {
                    String jsonData = response.body().string();
                    Log.v(GroupsActivity.class.getSimpleName(), jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
