package com.epicodus.pdxbranch.models;

import org.parceler.Parcel;

@Parcel
public class Post {
    private String author;
    private String authorImageUrl;
    private String authorId;
    private String content;
    private String pushId;

    public Post() {}

    public Post(String author, String authorImageUrl, String authorId, String content, String uid) {
        this.author = author;
        this.authorImageUrl = authorImageUrl;
        this.authorId = authorId;
        this.content = content;
        this.pushId = uid;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorImageUrl() {
        return authorImageUrl;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getContent() {
        return content;
    }

    public String getPushId() {
        return pushId;
    }
}
