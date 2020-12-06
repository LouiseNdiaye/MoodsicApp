package com.android.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.android.model.myPlaylist;

import java.util.List;


@Dao
public interface HistoryDAO {

    @Query("SELECT * FROM history_table")
    List<myPlaylist> getAllHistory();

    @Insert
    void insert(myPlaylist myplaylist);
}