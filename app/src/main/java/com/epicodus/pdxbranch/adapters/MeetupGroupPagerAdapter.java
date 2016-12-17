package com.epicodus.pdxbranch.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.pdxbranch.models.MeetupGroup;
import com.epicodus.pdxbranch.ui.MeetupGroupDetailFragment;

import java.util.ArrayList;

public class MeetupGroupPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<MeetupGroup> mMeetupGroups;

    public MeetupGroupPagerAdapter(FragmentManager fm, ArrayList<MeetupGroup> meetupGroups) {
        super(fm);
        mMeetupGroups = meetupGroups;
    }

    @Override
    public Fragment getItem(int position) {
        return MeetupGroupDetailFragment.newInstance(mMeetupGroups, position);
    }

    @Override
    public int getCount() {
        return mMeetupGroups.size();
    }
}
