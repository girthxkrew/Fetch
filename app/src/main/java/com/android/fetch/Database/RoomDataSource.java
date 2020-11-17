package com.android.fetch.Database;

import android.content.Context;

import com.android.fetch.Models.Results;
import com.android.fetch.Repository.ResultsDataSource;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class RoomDataSource implements ResultsDataSource {
    private static RoomDataSource instance = null;
    private ResultsDatabase resultsDatabase;

    private RoomDataSource(Context context) {
        resultsDatabase = ResultsDatabase.getInstance(context);
    }

    public static synchronized RoomDataSource getInstance(Context context) {
        if(instance == null) {
            instance = new RoomDataSource(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public Observable<List<Results>> getResults() {
        return resultsDatabase.resultsDAO().getResults();
    }

    @Override
    public void saveResults(List<Results> results) {
        resultsDatabase.resultsDAO().saveResults(results);
    }
}
