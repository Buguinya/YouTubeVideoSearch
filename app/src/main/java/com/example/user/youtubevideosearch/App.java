package com.example.user.youtubevideosearch;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {


    private Retrofit retrofit;
    private static VideoSearchApi videoSearchApi;

        private static final String BASE_URL = "https://www.googleapis.com";


    public static VideoSearchApi getVideoSearchApi() {
        return videoSearchApi;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        videoSearchApi = retrofit.create(VideoSearchApi.class);


    }
}
