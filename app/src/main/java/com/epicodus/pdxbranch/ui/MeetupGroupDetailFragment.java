package com.epicodus.pdxbranch.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.pdxbranch.R;
import com.epicodus.pdxbranch.models.MeetupGroup;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MeetupGroupDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.groupImageView) ImageView mGroupImage;
    @Bind(R.id.meetupLogo) ImageView mMeetupLogo;
    @Bind(R.id.groupName) TextView mGroupName;
    @Bind(R.id.numOfMembers) TextView mNumOfMembers;
    @Bind(R.id.groupDescription) TextView mGroupDescription;

    private MeetupGroup mMeetupGroup;

    public static MeetupGroupDetailFragment newInstance(MeetupGroup meetupGroup) {
        MeetupGroupDetailFragment meetupGroupDetailFragment = new MeetupGroupDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("meetupGroup", Parcels.wrap(meetupGroup));
        meetupGroupDetailFragment.setArguments(args);
        return meetupGroupDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMeetupGroup = Parcels.unwrap(getArguments().getParcelable("meetupGroup"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meetup_group_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mMeetupGroup.getmGroupPhotoThumb()).into(mGroupImage);
        mGroupName.setText(mMeetupGroup.getmName());
        mNumOfMembers.setText(mMeetupGroup.getmNumOfMembers().toString() + " members");
        mGroupDescription.setText(mMeetupGroup.getmDescription());

        mMeetupLogo.setOnClickListener(this);
        mGroupName.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mMeetupLogo || v == mGroupName) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mMeetupGroup.getmMeetupLink()));
            startActivity(webIntent);
        }
    }
}
