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
    private List<Member> friends = new ArrayList<Member>();

    public Member() {}

    public Member(String firstName, String lastName, String screenName, String zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.screenName = screenName;
        this.zipCode = zipCode;
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

    public List<Member> getFriends() {
        return friends;
    }
}
