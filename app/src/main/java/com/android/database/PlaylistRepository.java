package com.android.database;

import java.util.List;
import androidx.lifecycle.LiveData;

import com.android.DAO.PlaylistDAO;
import com.android.model.Playlist;
import com.android.model.Results;

public class PlaylistRepository {

    private PlaylistDAO playlistDAO;
    private static PlaylistRepository instance;

    private PlaylistRepository(){
        playlistDAO = PlaylistDAO.getInstance();
    }

    public static PlaylistRepository getInstance(){
        if(instance == null)
            instance = new PlaylistRepository();

        return instance;
    }

    public LiveData<List<Playlist>> getAllPlaylists(){
        return playlistDAO.getAllPlaylists();
    }

    public void insert(Playlist playlist) {
        playlistDAO.insert(playlist);
    }

    public void deleteAllPlaylists(){
        playlistDAO.deleteAllPlaylists();
    }


    public static Integer getIDPlaylist(Results answer) {
        if (answer.getEra().equals("option1")){
            if(answer.getPoints() < 50){
                return 1;
            }
            if(answer.getPoints() < 100 ){
                return 2;
            }
            if(answer.getPoints() < 200){
                return 3;
            } else {
                return 4;
            }
        } else {
            if(answer.getPoints() < 50){
                return 5;
            }
            if(answer.getPoints() < 100 ){
                return 6;
            }
            if(answer.getPoints() < 200){
                return 7;
            } else {
                return 8;
            }
        }
    }

}