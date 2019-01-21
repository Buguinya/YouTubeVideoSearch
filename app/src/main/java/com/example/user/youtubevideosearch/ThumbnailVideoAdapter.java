package com.example.user.youtubevideosearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.Collections;
import java.util.List;

public class ThumbnailVideoAdapter extends RecyclerView.Adapter<ThumbnailVideoAdapter.ThumbnailVideoViewHolder> {

    public static final String TAG = "My Tag";

    private Context mContext;

    public ThumbnailVideoAdapter(Context context){
        mContext = context;
    }


    private List<JsonVideo> videos = Collections.emptyList();


    public void setVideos(List<JsonVideo> videos) {
        this.videos = videos;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ThumbnailVideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item_thumbnail, viewGroup, false);
        return new ThumbnailVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ThumbnailVideoViewHolder thumbnailVideoViewHolder, final int i) {
        final YouTubeThumbnailLoader.OnThumbnailLoadedListener onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                Log.d(TAG, "onThumbnailLoaded: ");
                youTubeThumbnailView.setVisibility(View.VISIBLE);
                thumbnailVideoViewHolder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                Log.d(TAG, "onThumbnailError: " + errorReason);

            }
        };

        thumbnailVideoViewHolder.youTubeThumbnailView.initialize(MainActivity.KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                Log.d(TAG, "onInitializationSuccess:");
                youTubeThumbnailLoader.setVideo(videos.get(i).getId().getVideoId());
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onInitializationFailure");

            }
        });

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    class ThumbnailVideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        YouTubeThumbnailView youTubeThumbnailView;
        protected ImageView playButton;
        protected RelativeLayout relativeLayoutOverYouTubeThumbnailView;

        public ThumbnailVideoViewHolder(@NonNull View itemView) {
            super(itemView);
            youTubeThumbnailView = itemView.findViewById(R.id.youtube_thumbnail);
            playButton = itemView.findViewById(R.id.btnYoutube_player);
            playButton.setOnClickListener(this);
            relativeLayoutOverYouTubeThumbnailView = itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
        }

        @Override
        public void onClick(View v) {
            Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) mContext, MainActivity.KEY, videos.get(getLayoutPosition()).getId().getVideoId());
            mContext.startActivity(intent);
        }
    }
    }

