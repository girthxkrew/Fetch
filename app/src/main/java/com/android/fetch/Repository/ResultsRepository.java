package com.android.fetch.Repository;

import android.content.Context;
import android.util.Log;

import com.android.fetch.Database.RoomDataSource;
import com.android.fetch.Models.Results;
import com.android.fetch.Network.NetworkDataSource;
import com.android.fetch.Network.RetrofitSingleton;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class ResultsRepository implements ResultsDataSource {

    private static ResultsRepository instance = null;
    private RoomDataSource roomDataSource;
    private NetworkDataSource networkDataSource;

    private ResultsRepository(Context context) {
        roomDataSource = RoomDataSource.getInstance(context);
        networkDataSource = NetworkDataSource.getInstance();
    }

    public static synchronized ResultsRepository getInstance(Context context) {
        if(instance == null) {
            instance = new ResultsRepository(context);
        }
        return instance;
    }

    @Override
    public Observable<List<Results>> getResults() {
        return roomDataSource.getResults().flatMap(results -> {
            if(results.isEmpty()) {
                return networkDataSource.getResults().doOnNext(results1 -> saveResults(results1));
            }
            else {
                return Observable.just(results);
            }
        });
    }

    @Override
    public void saveResults(List<Results> results) {
        roomDataSource.saveResults(results);
    }
}
