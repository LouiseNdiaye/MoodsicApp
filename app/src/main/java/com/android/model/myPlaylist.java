package com.android.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history_table")
public class myPlaylist {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    @NonNull
    String date;
    String playlist;

    public myPlaylist(Integer id, String date, String playlist) {
        this.id = id;
        this.date = date;
        this.playlist = playlist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }
}
