package com.epicodus.pdxbranch.services;

import com.epicodus.pdxbranch.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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
}
