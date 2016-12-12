package com.epicodus.pdxbranch.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Member {
    private String firstName;
    private String lastName;
    private String screenName;
    private String zipCode;
    private String profileImageUrl;
    private String pushId;
    private List<Post> posts = new ArrayList<>();

    public Member() {}

    public Member(String firstName, String lastName, String screenName, String zipCode, String profileImageUrl, String uid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.screenName = screenName;
        this.zipCode = zipCode;
        this.profileImageUrl = profileImageUrl;
        this.pushId = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getPushId() {
        return pushId;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
