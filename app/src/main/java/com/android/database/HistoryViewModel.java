package com.android.database;

import android.app.Application;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.model.myPlaylist;

public class HistoryViewModel extends AndroidViewModel {

    private HistoryRepository repository;

    private LiveData<List<myPlaylist>> mAllHistory;

    public HistoryViewModel(Application application) {
        super(application);
        repository = new HistoryRepository(application);
        mAllHistory = repository.getAllHistory();
    }

    LiveData<List<myPlaylist>> getAllHistory() {
        return mAllHistory;
    }

    void insert(myPlaylist myPlaylist) {
        repository.insert(myPlaylist);
    }


}