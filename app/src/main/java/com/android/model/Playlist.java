package com.android.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "playlist_table")

public class Playlist {

    @PrimaryKey(autoGenerate = true)
    private int key;
    private String name;
    private String url;

    public Playlist(Integer key, String name, String url) {
        this.key = key;
        this.name = name;
        this.url = url;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
