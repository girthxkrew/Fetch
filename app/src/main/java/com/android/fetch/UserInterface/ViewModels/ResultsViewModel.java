package com.android.fetch.UserInterface.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.fetch.Models.Results;
import com.android.fetch.Repository.ResultsRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ResultsViewModel extends AndroidViewModel {
    private ResultsRepository resultsRepository;
    private Observable<List<Results>> getResults;
    private MutableLiveData<List<Results>> liveDataResults;
    public ResultsViewModel(@NonNull Application application) {
        super(application);
        resultsRepository = ResultsRepository.getInstance(application.getApplicationContext());
    }

    public LiveData<List<Results>> getResultsData() {
        if (liveDataResults == null) {
            liveDataResults = new MutableLiveData<>();
            getResults();
        }
        return liveDataResults;
    }

    private void getResults() {
        getResults = resultsRepository.getResults();
        getResults.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleSuccess, this::handleError);
    }

    private void handleSuccess(List<Results> results) {
        liveDataResults.postValue(results);
    }

    private void handleError(Throwable throwable) {
        Log.d("Error", throwable.getMessage());
    }

    public void unSubscribe() {
        getResults.unsubscribeOn(Schedulers.io());
    }


}
