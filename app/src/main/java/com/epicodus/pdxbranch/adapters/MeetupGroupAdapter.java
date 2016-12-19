package com.epicodus.pdxbranch.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.pdxbranch.R;
import com.epicodus.pdxbranch.models.MeetupGroup;
import com.epicodus.pdxbranch.ui.MeetupGroupDetailActivity;
import com.epicodus.pdxbranch.ui.MeetupGroupDetailFragment;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MeetupGroupAdapter extends RecyclerView.Adapter<MeetupGroupAdapter.MeetupGroupViewHolder> {
    private ArrayList<MeetupGroup> mMeetupGroups = new ArrayList<>();
    private Context mContext;

    public MeetupGroupAdapter(Context context, ArrayList<MeetupGroup> meetupGroups) {
        mContext = context;
        mMeetupGroups = meetupGroups;
    }

    @Override
    public MeetupGroupAdapter.MeetupGroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_list_item, parent, false);
        MeetupGroupViewHolder viewHolder = new MeetupGroupViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MeetupGroupAdapter.MeetupGroupViewHolder holder, int position) {
        holder.bindMeetupGroup(mMeetupGroups.get(position));
    }

    @Override
    public int getItemCount() {
        return mMeetupGroups.size();
    }

    public class MeetupGroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.groupImageView) ImageView mGroupImageView;
        @Bind(R.id.groupNameTextView) TextView mGroupName;
        @Bind(R.id.organizerTextView) TextView mOrganizerName;

        private Context mContext;
        private int mOrientation;

        public MeetupGroupViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);

            mOrientation = itemView.getResources().getConfiguration().orientation;
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }
        }

        private void createDetailFragment(int position) {
            MeetupGroupDetailFragment detailFragment = MeetupGroupDetailFragment.newInstance(mMeetupGroups, position);
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.meetupGroupDetailContainer, detailFragment);
            ft.commit();
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, MeetupGroupDetailActivity.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("meetupGroups", Parcels.wrap(mMeetupGroups));
                mContext.startActivity(intent);
            }
        }

        public void bindMeetupGroup(MeetupGroup meetupGroup) {
            String groupPhoto = meetupGroup.getmGroupPhotoThumb();
            if (!groupPhoto.equals("")) {
                Picasso.with(mContext).load(meetupGroup.getmGroupPhotoThumb()).into(mGroupImageView);
            }
            mGroupName.setText(meetupGroup.getmName());
            String organizer = "Organized by " + meetupGroup.getmOrganizerName();
            mOrganizerName.setText(organizer);
        }
    }
}
