package com.android.database;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.model.Playlist;

public class PlaylistViewModel extends ViewModel {

    private PlaylistRepository repository;

    public PlaylistViewModel() {
        repository = PlaylistRepository.getInstance();
    }

    public LiveData<List<Playlist>> getAllPlaylists() {
        return repository.getAllPlaylists();
    }

    public void insert(final Playlist playlist) {
        repository.insert(playlist);
    }

    public void deleteAllPlaylists() {
        repository.deleteAllPlaylists();
    }



}
