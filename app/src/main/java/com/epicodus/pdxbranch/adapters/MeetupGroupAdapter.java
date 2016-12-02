package com.epicodus.pdxbranch.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.pdxbranch.R;
import com.epicodus.pdxbranch.models.MeetupGroup;

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

    public class MeetupGroupViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.groupImageView) ImageView mGroupImageView;
        @Bind(R.id.groupNameTextView) TextView mGroupName;
        @Bind(R.id.organizerTextView) TextView mOrganizerName;

        private Context mContext;

        public MeetupGroupViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindMeetupGroup(MeetupGroup meetupGroup) {
            mGroupName.setText(meetupGroup.getmName());
            mOrganizerName.setText("Organized by " + meetupGroup.getmOrganizerName());
        }
    }
}
