package com.epicodus.pdxbranch.models;

import org.parceler.Parcel;

@Parcel
public class Post {
    private Member author;
    private Member recipient;
    private String content;

    public Post() {}

    public Post(Member author, Member recipient, String content) {
        this.author = author;
        this.recipient = recipient;
        this.content = content;
    }

    public Member getAuthor() {
        return author;
    }

    public Member getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }
}
