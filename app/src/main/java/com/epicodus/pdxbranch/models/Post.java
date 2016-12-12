package com.epicodus.pdxbranch.models;

import org.parceler.Parcel;

@Parcel
public class Post {
    private String content;
    private String pushId;

    public Post() {}

    public Post(String content, String uid) {
        this.content = content;
        this.pushId = uid;
    }

    public String getContent() {
        return content;
    }

    public String getPushId() {
        return pushId;
    }
}
