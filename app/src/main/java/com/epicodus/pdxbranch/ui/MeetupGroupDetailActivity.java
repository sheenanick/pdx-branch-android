package com.epicodus.pdxbranch.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.epicodus.pdxbranch.R;
import com.epicodus.pdxbranch.adapters.MeetupGroupPagerAdapter;
import com.epicodus.pdxbranch.models.MeetupGroup;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetupGroupDetailActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.meetupGroupsViewPager) ViewPager mViewPager;
    private MeetupGroupPagerAdapter adapterViewPager;
    ArrayList<MeetupGroup> mMeetupGroups = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetup_group);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mMeetupGroups = Parcels.unwrap(getIntent().getParcelableExtra("meetupGroups"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new MeetupGroupPagerAdapter(getSupportFragmentManager(), mMeetupGroups);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
