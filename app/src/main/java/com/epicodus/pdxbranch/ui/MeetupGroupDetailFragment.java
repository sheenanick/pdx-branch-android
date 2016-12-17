package com.epicodus.pdxbranch.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.pdxbranch.Constants;
import com.epicodus.pdxbranch.R;
import com.epicodus.pdxbranch.models.MeetupGroup;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MeetupGroupDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.groupImageView) ImageView mGroupImage;
    @Bind(R.id.meetupLogo) ImageView mMeetupLogo;
    @Bind(R.id.groupName) TextView mGroupName;
    @Bind(R.id.numOfMembers) TextView mNumOfMembers;
    @Bind(R.id.groupDescription) TextView mGroupDescription;
    @Bind(R.id.joinOnMeetupButton) Button mJoinButton;

    private ArrayList<MeetupGroup> mMeetupGroups;
    private int mPosition;
    private MeetupGroup mMeetupGroup;

    public static MeetupGroupDetailFragment newInstance(ArrayList<MeetupGroup> meetupGroups, int position) {
        MeetupGroupDetailFragment meetupGroupDetailFragment = new MeetupGroupDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.EXTRA_KEY_MEETUP_GROUPS, Parcels.wrap(meetupGroups));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);

        meetupGroupDetailFragment.setArguments(args);
        return meetupGroupDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMeetupGroups = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_MEETUP_GROUPS));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mMeetupGroup = mMeetupGroups.get(mPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meetup_group_detail, container, false);
        ButterKnife.bind(this, view);
        String groupPhoto = mMeetupGroup.getmGroupPhotoThumb();
        if (!groupPhoto.equals("")) {
            Picasso.with(view.getContext()).load(groupPhoto).into(mGroupImage);
        }
        mGroupName.setText(mMeetupGroup.getmName());
        mNumOfMembers.setText(mMeetupGroup.getmNumOfMembers().toString() + " members");
        mGroupDescription.setText(mMeetupGroup.getmDescription());

        mMeetupLogo.setOnClickListener(this);
        mGroupName.setOnClickListener(this);
        mJoinButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mMeetupLogo || v == mGroupName || v == mJoinButton) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mMeetupGroup.getmMeetupLink()));
            startActivity(webIntent);
        }
    }
}
