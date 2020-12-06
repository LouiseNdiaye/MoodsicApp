package com.android.DAO;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.model.Playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {

    private static MutableLiveData<List<Playlist>> allPlaylists;
    private static PlaylistDAO instance;

    private PlaylistDAO() {
        allPlaylists = new MutableLiveData<>();
        List<Playlist> newList = new ArrayList<>();
        allPlaylists.setValue(newList);
    }

    public static PlaylistDAO getInstance(){
        if(instance == null) {
            instance = new PlaylistDAO();
        }
        return instance;
    }

    public LiveData<List<Playlist>> getAllPlaylists() {
        return allPlaylists;
    }

    public void insert(Playlist playlist) {
        List<Playlist> currentPlaylists = allPlaylists.getValue();
        currentPlaylists.add(playlist);
        allPlaylists.setValue(currentPlaylists);
    }

    public void deleteAllPlaylists() {
        List<Playlist> newList = new ArrayList<>();
        allPlaylists.setValue(newList);
    }

}