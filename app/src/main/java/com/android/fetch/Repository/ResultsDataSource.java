package com.android.fetch.Repository;

import com.android.fetch.Models.Results;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface ResultsDataSource {

    public Observable<List<Results>> getResults();

    public void saveResults(List<Results> results);
}
