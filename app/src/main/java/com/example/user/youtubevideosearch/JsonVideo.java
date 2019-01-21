package com.example.user.youtubevideosearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


class JsonVideo {

            @SerializedName("id")
            @Expose
            private Id id;

            public Id getId() {
                return id;
            }

            public void setId(Id id) {
                this.id = id;
            }


            public class Id {
                @SerializedName("videoId")
                @Expose
                private String videoId;

                    public String getVideoId() {
                        return videoId;
                    }

                    public void setVideoId(String videoId) {
                        this.videoId = videoId;
                    }
                }
    }

