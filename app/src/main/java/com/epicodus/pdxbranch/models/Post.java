package com.epicodus.pdxbranch.models;

import org.parceler.Parcel;

@Parcel
public class Post {
    private String author;
    private String content;
    private String pushId;

    public Post() {}

    public Post(String author, String content, String uid) {
        this.author = author;
        this.content = content;
        this.pushId = uid;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getPushId() {
        return pushId;
    }
}
