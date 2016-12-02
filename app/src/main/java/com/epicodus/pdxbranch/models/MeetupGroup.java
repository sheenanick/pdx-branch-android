package com.epicodus.pdxbranch.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class MeetupGroup {
    private String mName;
    private String mMeetupLink;
    private String mDescription;
    private int mDateCreated;
    private Integer mNumOfMembers;
    private String mOrganizerName;
    private String mOrganizerPhotoThumb;
    private String mGroupPhotoThumb;
    private ArrayList<String> mPhotos;

    public MeetupGroup() {}

    public MeetupGroup(String mName, String mMeetupLink, String mDescription, int mDateCreated, Integer mNumOfMembers, String mOrganizerName, String mOrganizerPhotoThumb, String mGroupPhotoThumb, ArrayList<String> mPhotos) {
        this.mName = mName;
        this.mMeetupLink = mMeetupLink;
        this.mDescription = mDescription;
        this.mDateCreated = mDateCreated;
        this.mNumOfMembers = mNumOfMembers;
        this.mOrganizerName = mOrganizerName;
        this.mOrganizerPhotoThumb = mOrganizerPhotoThumb;
        this.mGroupPhotoThumb = mGroupPhotoThumb;
        this.mPhotos = mPhotos;
    }

    public String getmName() {
        return mName;
    }

    public String getmMeetupLink() {
        return mMeetupLink;
    }

    public String getmDescription() {
        return mDescription;
    }

    public int getmDateCreated() {
        return mDateCreated;
    }

    public Integer getmNumOfMembers() {
        return mNumOfMembers;
    }

    public String getmOrganizerName() {
        return mOrganizerName;
    }

    public String getmOrganizerPhotoThumb() {
        return mOrganizerPhotoThumb;
    }

    public String getmGroupPhotoThumb() {
        return mGroupPhotoThumb;
    }

    public ArrayList<String> getmPhotos() {
        return mPhotos;
    }
}
