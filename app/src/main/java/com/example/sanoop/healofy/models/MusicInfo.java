package com.example.sanoop.healofy.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MusicInfo {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Artist")
    @Expose
    private String artist;
    @SerializedName("Album")
    @Expose
    private String album;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
