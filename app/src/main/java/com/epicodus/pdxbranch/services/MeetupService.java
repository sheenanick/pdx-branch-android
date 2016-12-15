package com.epicodus.pdxbranch.services;

import android.text.Html;

import com.epicodus.pdxbranch.Constants;
import com.epicodus.pdxbranch.models.MeetupGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MeetupService {

    public static void findRecommendedGroups(String query, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MEETUP_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.MEETUP_API_KEY_PARAMETER, Constants.MEETUP_API_KEY);
        if (query != null) {
            urlBuilder.addQueryParameter(Constants.PREFERENCES_SEARCH_KEY, query);
        }
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<MeetupGroup> processResults(Response response) {
        ArrayList<MeetupGroup> meetupGroups = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONArray meetupGroupsJSON = new JSONArray(jsonData);
                for (int i = 0; i < meetupGroupsJSON.length(); i++) {
                    JSONObject meetupGroupJSON = meetupGroupsJSON.getJSONObject(i);

                    String name = meetupGroupJSON.getString("name");
                    String link = meetupGroupJSON.getString("link");
                    String description = meetupGroupJSON.getString("description");
                    description = Html.fromHtml(description).toString();
                    int date = meetupGroupJSON.getInt("created");
                    Integer members = meetupGroupJSON.getInt("members");

                    JSONObject organizer = meetupGroupJSON.getJSONObject("organizer");
                    String organizerName = organizer.getString("name");
                    JSONObject organizerPhoto = organizer.optJSONObject("photo");
                    String organizerPhotoLink ="";
                    if (organizerPhoto != null) {
                        organizerPhotoLink = organizerPhoto.getString("thumb_link");
                    }

                    JSONObject groupPhoto = meetupGroupJSON.optJSONObject("group_photo");
                    String groupPhotoLink = "";
                    if (groupPhoto != null) {
                        groupPhotoLink = groupPhoto.getString("photo_link");
                    }

                    JSONArray photosJSON = meetupGroupJSON.optJSONArray("photos");
                    ArrayList<String> photos = new ArrayList<>();
                    if (photosJSON != null) {
                        for (int j = 0; j < photosJSON.length(); j++) {
                            String photoLink = photosJSON.getJSONObject(j).getString("thumb_link");
                            photos.add(photoLink);
                        }
                    }

                    MeetupGroup meetupGroup = new MeetupGroup(name, link, description, date, members, organizerName, organizerPhotoLink, groupPhotoLink, photos);
                    meetupGroups.add(meetupGroup);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return meetupGroups;
    }
}