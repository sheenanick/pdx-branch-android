package com.epicodus.pdxbranch.services;

import android.util.Log;

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

    public static void findRecommendedGroups(Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MEETUP_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.MEETUP_API_KEY_PARAMETER, Constants.MEETUP_API_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<MeetupGroup> processResults(Response response) {
        ArrayList<MeetupGroup> meetupGroups = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            Log.d("JSON", jsonData);
            if (response.isSuccessful()) {
                JSONArray meetupGroupsJSON = new JSONArray(jsonData);
                for (int i = 0; i < meetupGroupsJSON.length(); i++) {
                    Log.d("TEST", i + "Group!");
                    JSONObject meetupGroupJSON = meetupGroupsJSON.getJSONObject(i);

                    String name = meetupGroupJSON.getString("name");
                    String link = meetupGroupJSON.getString("link");
                    String description = meetupGroupJSON.getString("description");
                    int date = meetupGroupJSON.getInt("created");
                    int members = meetupGroupJSON.getInt("members");

                    JSONObject organizer = meetupGroupJSON.getJSONObject("organizer");
                    String organizerName = organizer.getString("name");
                    JSONObject organizerPhoto = organizer.optJSONObject("photo");
                    String organizerPhotoLink;
                    if (organizerPhoto != null) {
                        organizerPhotoLink = organizerPhoto.getString("thumb_link");
                    } else {
                        organizerPhotoLink = "http://www.pngall.com/wp-content/uploads/2016/05/Branch-Free-Download-PNG.png";
                    }

                    JSONObject groupPhoto = meetupGroupJSON.optJSONObject("group_photo");
                    String groupPhotoLink;
                    if (groupPhoto != null) {
                        groupPhotoLink = groupPhoto.getString("thumb_link");
                    } else {
                        groupPhotoLink = "http://www.pngall.com/wp-content/uploads/2016/05/Branch-Free-Download-PNG.png";
                    }

                    JSONArray photosJSON = meetupGroupJSON.getJSONArray("photos");
                    ArrayList<String> photos = new ArrayList<>();
                    for (int j = 0; j < photosJSON.length(); j++) {
                        String photoLink = photosJSON.getJSONObject(j).getString("thumb_link");
                        photos.add(photoLink);
                    }

                    MeetupGroup meetupGroup = new MeetupGroup(name, link, description, date, members, organizerName, organizerPhotoLink, groupPhotoLink, photos);
                    meetupGroups.add(meetupGroup);
                    Log.d("GROUPS", meetupGroup.getmName());
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