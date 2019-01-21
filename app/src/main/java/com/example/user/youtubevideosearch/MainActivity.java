package com.example.user.youtubevideosearch;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends YouTubeBaseActivity implements View.OnClickListener{

        public static final String KEY = "AIzaSyAdac6e69PTicfGarfhf1g1DJlTQs_Jbck";
    private static final String TAG = "TAG";

    private RecyclerView recyclerView;
    private EditText searchET;
    private Button searchBtn;

    private ThumbnailVideoAdapter thumbnailVideoAdapter;
    private List<JsonVideo> mVideos;
    private RecyclerView mVideoRecycler;

    private List<JsonVideo> videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        searchBtn = findViewById(R.id.search_btn);
        searchET = findViewById(R.id.et_search);
        videos = new ArrayList<>();

        searchBtn.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        thumbnailVideoAdapter = new ThumbnailVideoAdapter(MainActivity.this);
        recyclerView.setAdapter(thumbnailVideoAdapter);





    }

    private void loadVideo() {
        App.getVideoSearchApi().callback(searchET.getText().toString()).enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                Log.d(TAG, "onResponse: ");
                videos = response.body().getVideos();
                thumbnailVideoAdapter.setVideos(videos);

            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
                Log.d(TAG, "onFailure:");
            }
        });
    }




    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.search_btn){
            if (!TextUtils.isEmpty(searchET.getText().toString())){
                loadVideo();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
