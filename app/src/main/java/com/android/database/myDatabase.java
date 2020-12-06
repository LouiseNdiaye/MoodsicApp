package com.android.database;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.android.DAO.HistoryDAO;
import com.android.model.myPlaylist;

@Database(entities = {myPlaylist.class}, version = 2)

public abstract class myDatabase extends RoomDatabase {

    public abstract HistoryDAO historyDAO();
    private static volatile myDatabase INSTANCE;

    public static myDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (myDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            myDatabase.class, "myDatabase")
                            .allowMainThreadQueries()
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}