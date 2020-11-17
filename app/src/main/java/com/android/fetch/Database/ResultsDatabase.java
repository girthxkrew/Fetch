package com.android.fetch.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.android.fetch.Models.Results;

@Database(entities = {Results.class}, version = 1)
public abstract class ResultsDatabase extends RoomDatabase {

    public abstract ResultsDAO resultsDAO();

    private static volatile ResultsDatabase instance;

    public static synchronized ResultsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ResultsDatabase.class, "results.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
