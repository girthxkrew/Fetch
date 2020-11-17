package com.android.fetch.Network;

import com.android.fetch.Models.Results;
import com.android.fetch.Repository.ResultsDataSource;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class NetworkDataSource implements ResultsDataSource {

    private static NetworkDataSource instance;
    private FetchAPI fetchAPI;

    private NetworkDataSource() {
        fetchAPI = RetrofitSingleton.getInstance().create(FetchAPI.class);
    }

    public static synchronized NetworkDataSource getInstance() {
        if(instance == null) {
            instance = new NetworkDataSource();
        }
        return instance;
    }

    @Override
    public Observable<List<Results>> getResults() {
        return fetchAPI.getResults();
    }

    @Override
    public void saveResults(List<Results> results) {

    }
}
