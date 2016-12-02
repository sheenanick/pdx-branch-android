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
            if (response.isSuccessful()) {
                JSONArray meetupGroupsJSON = new JSONArray(jsonData);
                for (int i = 0; i < meetupGroupsJSON.length(); i++) {

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
