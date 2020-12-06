package com.android.database;

import android.app.Application;
import android.util.Log;

import java.util.List;
import androidx.lifecycle.LiveData;

import com.android.DAO.HistoryDAO;
import com.android.model.myPlaylist;

public class HistoryRepository {

    private HistoryDAO historyDAO;
    private LiveData<List<myPlaylist>> mAllHistory;

    public HistoryRepository(Application application){
        myDatabase db = myDatabase.getDatabase(application);
        historyDAO = db.historyDAO();
    }

    LiveData<List<myPlaylist>> getAllHistory() {
        return mAllHistory;
    }

    public void insert(myPlaylist myPlaylist){
        historyDAO.insert(myPlaylist);
    }
}